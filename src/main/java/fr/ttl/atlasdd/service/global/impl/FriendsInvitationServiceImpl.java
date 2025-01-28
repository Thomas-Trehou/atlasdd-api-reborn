package fr.ttl.atlasdd.service.global.impl;

import fr.ttl.atlasdd.repository.FriendsInvitationRepo;
import fr.ttl.atlasdd.repository.UserRepo;
import fr.ttl.atlasdd.service.global.FriendsInvitationService;
import fr.ttl.atlasdd.sqldto.FriendInvitationSqlDto;
import fr.ttl.atlasdd.sqldto.UserSqlDto;
import fr.ttl.atlasdd.utils.InvitationStatus;
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

        UserSqlDto sender = userRepo.findById(senderId).orElseThrow();
        UserSqlDto receiver = userRepo.findById(receiverId).orElseThrow();

        boolean invitationAlreadyExist =  friendsInvitationRepo.existsByRequestUser_IdAndReceiverUser_Id(senderId, receiverId);

        if (!invitationAlreadyExist) {
            FriendInvitationSqlDto friendInvitationSqlDto = new FriendInvitationSqlDto();

            friendInvitationSqlDto.setRequestUser(sender);
            friendInvitationSqlDto.setReceiverUser(receiver);
            friendInvitationSqlDto.setStatus(InvitationStatus.PENDING);

            friendsInvitationRepo.save(friendInvitationSqlDto);
        }
    }

    @Override
    public void acceptInvitation(Long invitationId, Long receiverId) {
        FriendInvitationSqlDto friendInvitationSqlDto = friendsInvitationRepo.findByIdAndReceiverUser_Id(invitationId, receiverId);

        if(friendInvitationSqlDto == null) {
            throw new IllegalArgumentException("Invitation not found");
        }

        UserSqlDto sender = friendInvitationSqlDto.getRequestUser();
        UserSqlDto receiver = friendInvitationSqlDto.getReceiverUser();

        sender.getFriends().add(receiver);
        receiver.getFriends().add(sender);

        userRepo.save(sender);
        userRepo.save(receiver);

        friendInvitationSqlDto.setStatus(InvitationStatus.ACCEPTED);

        friendsInvitationRepo.save(friendInvitationSqlDto);
    }

    @Override
    public void declineInvitation(Long invitationId, Long receiverId) {
        FriendInvitationSqlDto friendInvitationSqlDto = friendsInvitationRepo.findByIdAndReceiverUser_Id(invitationId, receiverId);

        if(friendInvitationSqlDto == null) {
            throw new IllegalArgumentException("Invitation not found");
        }

        friendsInvitationRepo.delete(friendInvitationSqlDto);
    }

    @Override
    public void cancelInvitation(Long invitationId, Long senderId) {
        FriendInvitationSqlDto friendInvitationSqlDto = friendsInvitationRepo.findByIdAndRequestUser_Id(invitationId, senderId);

        if(friendInvitationSqlDto == null) {
            throw new IllegalArgumentException("Invitation not found");
        }

        friendsInvitationRepo.delete(friendInvitationSqlDto);
    }
}
