package fr.ttl.atlasdd.service.user;

import org.springframework.stereotype.Service;

@Service
public interface FriendsInvitationService {

    void sendInvitation(Long senderId, Long receiverId);

    void acceptInvitation(Long invitationId, Long userId);

    void declineInvitation(Long invitationId, Long userId);

    void cancelInvitation(Long invitationId, Long userId);
}
