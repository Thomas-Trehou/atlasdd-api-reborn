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
                .orElseThrow(() -> new UserNotFoundException("Utilisateur non trouvé"));;
        UserSqlDto receiver = userRepo.findById(receiverId)
                .orElseThrow(() -> new UserNotFoundException("Utilisateur non trouvé"));

        boolean invitationAlreadyExist =  friendsInvitationRepo.existsByRequestUser_IdAndReceiverUser_Id(senderId, receiverId);

        if (!invitationAlreadyExist) {
            FriendInvitationSqlDto friendInvitationSqlDto = new FriendInvitationSqlDto();

            friendInvitationSqlDto.setRequestUser(sender);
            friendInvitationSqlDto.setReceiverUser(receiver);
            friendInvitationSqlDto.setStatus(InvitationStatus.PENDING);

            try {
                friendsInvitationRepo.save(friendInvitationSqlDto);
            } catch (Exception e) {
                throw new FriendsInvitationSavingErrorException("Erreur lors de la sauvegarde de l'invitation");
            }
        } else {
            throw new FriendsInvitationSavingErrorException("Invitation déjà envoyée");
        }

    }

    @Override
    public void acceptInvitation(Long invitationId, Long receiverId) {
        FriendInvitationSqlDto friendInvitationSqlDto = friendsInvitationRepo.findByIdAndReceiverUser_Id(invitationId, receiverId);

        if(friendInvitationSqlDto == null) {
            throw new FriendsInvitationNotFoundException("Invitation non trouvée");
        }

        UserSqlDto sender = friendInvitationSqlDto.getRequestUser();
        UserSqlDto receiver = friendInvitationSqlDto.getReceiverUser();

        sender.getFriends().add(receiver);
        receiver.getFriends().add(sender);

        try {
            userRepo.save(sender);
            userRepo.save(receiver);
        } catch (Exception e) {
            throw new UserSavingErrorException("Erreur lors de la sauvegarde de l'utilisateur");
        }

        friendInvitationSqlDto.setStatus(InvitationStatus.ACCEPTED);

        try {
            friendsInvitationRepo.save(friendInvitationSqlDto);
        } catch (Exception e) {
            throw new FriendsInvitationSavingErrorException("Erreur lors de la sauvegarde de l'invitation");
        }
    }

    @Override
    public void declineInvitation(Long invitationId, Long receiverId) {
        FriendInvitationSqlDto friendInvitationSqlDto = friendsInvitationRepo.findByIdAndReceiverUser_Id(invitationId, receiverId);

        if(friendInvitationSqlDto == null) {
            throw new FriendsInvitationNotFoundException("Invitation non trouvée");
        }

        try {
            friendsInvitationRepo.delete(friendInvitationSqlDto);
        } catch (Exception e) {
            throw new FriendsInvitationSavingErrorException("Erreur lors du refus de l'invitation");
        }

    }

    @Override
    public void cancelInvitation(Long invitationId, Long senderId) {
        FriendInvitationSqlDto friendInvitationSqlDto = friendsInvitationRepo.findByIdAndRequestUser_Id(invitationId, senderId);

        if(friendInvitationSqlDto == null) {
            throw new FriendsInvitationNotFoundException("Invitation non trouvée");
        }

        try {
            friendsInvitationRepo.delete(friendInvitationSqlDto);
        } catch (Exception e) {
            throw new FriendsInvitationSavingErrorException("Erreur lors de l'annulation de l'invitation");
        }
    }
}
