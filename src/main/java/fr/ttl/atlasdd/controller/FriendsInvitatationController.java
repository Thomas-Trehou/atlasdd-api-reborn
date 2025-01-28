package fr.ttl.atlasdd.controller;

import fr.ttl.atlasdd.service.global.FriendsInvitationService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/friends-invitation")
public class FriendsInvitatationController {

    private final FriendsInvitationService friendsInvitationService;

    public FriendsInvitatationController(FriendsInvitationService friendsInvitationService) {
        this.friendsInvitationService = friendsInvitationService;
    }

    @PostMapping("/{senderId}/to/{receiverId}")
    public void sendInvitation(@PathVariable Long senderId,@PathVariable Long receiverId) {
        friendsInvitationService.sendInvitation(senderId, receiverId);
    }

    @PatchMapping("/{invitationId}/accept/{receiverId}")
    public void acceptInvitation(@PathVariable Long invitationId, @PathVariable Long receiverId) {
        friendsInvitationService.acceptInvitation(invitationId, receiverId);
    }

    @DeleteMapping("/{invitationId}/decline/{receiverId}")
    public void declineInvitation(@PathVariable Long invitationId, @PathVariable Long receiverId) {
        friendsInvitationService.declineInvitation(invitationId, receiverId);
    }

    @DeleteMapping("/{invitationId}/cancel/{senderId}")
    public void cancelInvitation(@PathVariable Long invitationId, @PathVariable Long senderId) {
        friendsInvitationService.cancelInvitation(invitationId, senderId);
    }
}
