package fr.ttl.atlasdd.service.user.impl;

import fr.ttl.atlasdd.apidto.user.FriendInvitationApiDto;
import fr.ttl.atlasdd.entity.user.User;
import fr.ttl.atlasdd.exception.user.FriendsInvitationNotFoundException;
import fr.ttl.atlasdd.exception.user.FriendsInvitationSavingErrorException;
import fr.ttl.atlasdd.exception.user.UserNotFoundException;
import fr.ttl.atlasdd.exception.user.UserSavingErrorException;
import fr.ttl.atlasdd.mapper.user.FriendInvitationMapper;
import fr.ttl.atlasdd.repository.user.FriendsInvitationRepo;
import fr.ttl.atlasdd.repository.user.UserRepo;
import fr.ttl.atlasdd.service.user.FriendsInvitationService;
import fr.ttl.atlasdd.entity.user.FriendInvitation;
import fr.ttl.atlasdd.utils.exception.ExceptionMessage;
import fr.ttl.atlasdd.utils.user.InvitationStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FriendsInvitationServiceImpl implements FriendsInvitationService {

    private final FriendsInvitationRepo friendsInvitationRepo;
    private final UserRepo userRepo;
    private final FriendInvitationMapper friendInvitationMapper;

    @Override
    @Transactional
     public void sendInvitation(Long senderId, Long receiverId) {

        User sender = userRepo.findById(senderId)
                .orElseThrow(() -> new UserNotFoundException(ExceptionMessage.USER_NOT_FOUND.getMessage()));
        User receiver = userRepo.findById(receiverId)
                .orElseThrow(() -> new UserNotFoundException(ExceptionMessage.USER_NOT_FOUND.getMessage()));

        boolean invitationAlreadyExist =  friendsInvitationRepo.existsByRequestUser_IdAndReceiverUser_Id(senderId, receiverId);

        if (!invitationAlreadyExist) {
            FriendInvitation friendInvitation = new FriendInvitation();

            friendInvitation.setRequestUser(sender);
            friendInvitation.setReceiverUser(receiver);
            friendInvitation.setStatus(InvitationStatus.PENDING);

            try {
                friendsInvitationRepo.save(friendInvitation);
            } catch (Exception e) {
                throw new FriendsInvitationSavingErrorException(ExceptionMessage.FRIENDS_INVITATION_SAVE_ERROR.getMessage());
            }
        } else {
            throw new FriendsInvitationSavingErrorException(ExceptionMessage.FRIENDS_INVITATION_ALREADY_SENT.getMessage());
        }

    }

    @Override
    @Transactional
    public void acceptInvitation(Long invitationId, Long receiverId) {
        FriendInvitation friendInvitation = friendsInvitationRepo.findByIdAndReceiverUser_Id(invitationId, receiverId);

        if(friendInvitation == null) {
            throw new FriendsInvitationNotFoundException(ExceptionMessage.FRIENDS_INVITATION_NOT_FOUND.getMessage());
        }

        User sender = friendInvitation.getRequestUser();
        User receiver = friendInvitation.getReceiverUser();

        sender.getFriends().add(receiver);
        receiver.getFriends().add(sender);

        try {
            userRepo.save(sender);
            userRepo.save(receiver);
        } catch (Exception e) {
            throw new UserSavingErrorException(ExceptionMessage.USER_SAVE_ERROR.getMessage());
        }

        friendInvitation.setStatus(InvitationStatus.ACCEPTED);

        try {
            friendsInvitationRepo.save(friendInvitation);
        } catch (Exception e) {
            throw new FriendsInvitationSavingErrorException(ExceptionMessage.FRIENDS_INVITATION_ACCEPT_ERROR.getMessage());
        }
    }

    @Override
    @Transactional
    public void declineInvitation(Long invitationId, Long receiverId) {
        FriendInvitation friendInvitation = friendsInvitationRepo.findByIdAndReceiverUser_Id(invitationId, receiverId);

        if(friendInvitation == null) {
            throw new FriendsInvitationNotFoundException(ExceptionMessage.FRIENDS_INVITATION_NOT_FOUND.getMessage());
        }

        try {
            friendsInvitationRepo.delete(friendInvitation);
        } catch (Exception e) {
            throw new FriendsInvitationSavingErrorException(ExceptionMessage.FRIENDS_INVITATION_DECLINE_ERROR.getMessage());
        }

    }

    @Override
    @Transactional
    public void cancelInvitation(Long invitationId, Long senderId) {
        FriendInvitation friendInvitation = friendsInvitationRepo.findByIdAndRequestUser_Id(invitationId, senderId);

        if(friendInvitation == null) {
            throw new FriendsInvitationNotFoundException(ExceptionMessage.FRIENDS_INVITATION_NOT_FOUND.getMessage());
        }

        try {
            friendsInvitationRepo.delete(friendInvitation);
        } catch (Exception e) {
            throw new FriendsInvitationSavingErrorException(ExceptionMessage.FRIENDS_INVITATION_CANCEL_ERROR.getMessage());
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<FriendInvitationApiDto> getInvitations(Long userId) {
        userRepo.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(ExceptionMessage.USER_NOT_FOUND.getMessage()));

        List<FriendInvitation> invitations = friendsInvitationRepo.findByUserAndStatus(userId, InvitationStatus.PENDING);

        return invitations.stream().map(friendInvitationMapper::toApiDto).toList();
    }
}
