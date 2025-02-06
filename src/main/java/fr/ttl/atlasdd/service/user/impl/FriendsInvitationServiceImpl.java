package fr.ttl.atlasdd.service.user.impl;

import fr.ttl.atlasdd.exception.user.FriendsInvitationNotFoundException;
import fr.ttl.atlasdd.exception.user.FriendsInvitationSavingErrorException;
import fr.ttl.atlasdd.exception.user.UserNotFoundException;
import fr.ttl.atlasdd.exception.user.UserSavingErrorException;
import fr.ttl.atlasdd.repository.user.FriendsInvitationRepo;
import fr.ttl.atlasdd.repository.user.UserRepo;
import fr.ttl.atlasdd.service.user.FriendsInvitationService;
import fr.ttl.atlasdd.sqldto.user.FriendInvitationSqlDto;
import fr.ttl.atlasdd.sqldto.user.UserSqlDto;
import fr.ttl.atlasdd.utils.exception.ExceptionMessage;
import fr.ttl.atlasdd.utils.user.InvitationStatus;
import org.springframework.stereotype.Service;

@Service
public class FriendsInvitationServiceImpl implements FriendsInvitationService {

    private final FriendsInvitationRepo friendsInvitationRepo;
    private final UserRepo userRepo;

    public FriendsInvitationServiceImpl(FriendsInvitationRepo friendsInvitationRepo, UserRepo userRepo) {
        this.friendsInvitationRepo = friendsInvitationRepo;
        this.userRepo = userRepo;
    }

    @Override
     public void sendInvitation(Long senderId, Long receiverId) {

        UserSqlDto sender = userRepo.findById(senderId)
                .orElseThrow(() -> new UserNotFoundException(ExceptionMessage.USER_NOT_FOUND.getMessage()));
        UserSqlDto receiver = userRepo.findById(receiverId)
                .orElseThrow(() -> new UserNotFoundException(ExceptionMessage.USER_NOT_FOUND.getMessage()));

        boolean invitationAlreadyExist =  friendsInvitationRepo.existsByRequestUser_IdAndReceiverUser_Id(senderId, receiverId);

        if (!invitationAlreadyExist) {
            FriendInvitationSqlDto friendInvitationSqlDto = new FriendInvitationSqlDto();

            friendInvitationSqlDto.setRequestUser(sender);
            friendInvitationSqlDto.setReceiverUser(receiver);
            friendInvitationSqlDto.setStatus(InvitationStatus.PENDING);

            try {
                friendsInvitationRepo.save(friendInvitationSqlDto);
            } catch (Exception e) {
                throw new FriendsInvitationSavingErrorException(ExceptionMessage.FRIENDS_INVITATION_SAVE_ERROR.getMessage());
            }
        } else {
            throw new FriendsInvitationSavingErrorException(ExceptionMessage.FRIENDS_INVITATION_ALREADY_SENT.getMessage());
        }

    }

    @Override
    public void acceptInvitation(Long invitationId, Long receiverId) {
        FriendInvitationSqlDto friendInvitationSqlDto = friendsInvitationRepo.findByIdAndReceiverUser_Id(invitationId, receiverId);

        if(friendInvitationSqlDto == null) {
            throw new FriendsInvitationNotFoundException(ExceptionMessage.FRIENDS_INVITATION_NOT_FOUND.getMessage());
        }

        UserSqlDto sender = friendInvitationSqlDto.getRequestUser();
        UserSqlDto receiver = friendInvitationSqlDto.getReceiverUser();

        sender.getFriends().add(receiver);
        receiver.getFriends().add(sender);

        try {
            userRepo.save(sender);
            userRepo.save(receiver);
        } catch (Exception e) {
            throw new UserSavingErrorException(ExceptionMessage.USER_SAVE_ERROR.getMessage());
        }

        friendInvitationSqlDto.setStatus(InvitationStatus.ACCEPTED);

        try {
            friendsInvitationRepo.save(friendInvitationSqlDto);
        } catch (Exception e) {
            throw new FriendsInvitationSavingErrorException(ExceptionMessage.FRIENDS_INVITATION_ACCEPT_ERROR.getMessage());
        }
    }

    @Override
    public void declineInvitation(Long invitationId, Long receiverId) {
        FriendInvitationSqlDto friendInvitationSqlDto = friendsInvitationRepo.findByIdAndReceiverUser_Id(invitationId, receiverId);

        if(friendInvitationSqlDto == null) {
            throw new FriendsInvitationNotFoundException(ExceptionMessage.FRIENDS_INVITATION_NOT_FOUND.getMessage());
        }

        try {
            friendsInvitationRepo.delete(friendInvitationSqlDto);
        } catch (Exception e) {
            throw new FriendsInvitationSavingErrorException(ExceptionMessage.FRIENDS_INVITATION_DECLINE_ERROR.getMessage());
        }

    }

    @Override
    public void cancelInvitation(Long invitationId, Long senderId) {
        FriendInvitationSqlDto friendInvitationSqlDto = friendsInvitationRepo.findByIdAndRequestUser_Id(invitationId, senderId);

        if(friendInvitationSqlDto == null) {
            throw new FriendsInvitationNotFoundException(ExceptionMessage.FRIENDS_INVITATION_NOT_FOUND.getMessage());
        }

        try {
            friendsInvitationRepo.delete(friendInvitationSqlDto);
        } catch (Exception e) {
            throw new FriendsInvitationSavingErrorException(ExceptionMessage.FRIENDS_INVITATION_CANCEL_ERROR.getMessage());
        }
    }
}
