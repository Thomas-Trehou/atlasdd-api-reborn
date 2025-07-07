package fr.ttl.atlasdd.service.user;

import fr.ttl.atlasdd.apidto.user.FriendInvitationApiDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface FriendsInvitationService {

    void sendInvitation(Long senderId, Long receiverId);

    void acceptInvitation(Long invitationId, Long userId);

    void declineInvitation(Long invitationId, Long userId);

    void cancelInvitation(Long invitationId, Long userId);

    List<FriendInvitationApiDto> getInvitations(Long userId);
}
