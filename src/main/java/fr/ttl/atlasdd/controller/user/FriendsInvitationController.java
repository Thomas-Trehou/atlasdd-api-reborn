package fr.ttl.atlasdd.controller.user;

import fr.ttl.atlasdd.service.user.FriendsInvitationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/friends-invitations")
@RequiredArgsConstructor
public class FriendsInvitationController {

    private final FriendsInvitationService friendsInvitationService;

    @Operation(summary = "Send a friend invitation to a user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Invitation sent"),
            @ApiResponse(responseCode = "400", description = "User already has a pending invitation", content = @Content),
            @ApiResponse(responseCode = "404", description = "User not found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Error at invitation sending", content = @Content)
    })
    @PostMapping("/{senderId}/to/{receiverId}")
    public void sendInvitation(
            @Parameter(description = "ID of the sender", required = true)
            @PathVariable Long senderId,
            @Parameter(description = "ID of the receiver", required = true)
            @PathVariable Long receiverId
    ) {
        friendsInvitationService.sendInvitation(senderId, receiverId);
    }

    @Operation(summary = "Accept a friend invitation")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Invitation accepted"),
            @ApiResponse(responseCode = "404", description =
                    "Invitation not found / " +
                    "User not found", content = @Content),
            @ApiResponse(responseCode = "500", description =
                    "Error at user saving / " +
                    "Error at invitation saving", content = @Content)
    })
    @PatchMapping("/{invitationId}/accept/{receiverId}")
    public void acceptInvitation(
            @Parameter(description = "ID of the invitation", required = true)
            @PathVariable Long invitationId,
            @Parameter(description = "ID of the receiver", required = true)
            @PathVariable Long receiverId
    ) {
        friendsInvitationService.acceptInvitation(invitationId, receiverId);
    }

    @Operation(summary = "Decline a friend invitation")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Invitation declined"),
            @ApiResponse(responseCode = "404", description =
                    "Invitation not found / " +
                    "User not found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Error at invitation saving", content = @Content)
    })
    @DeleteMapping("/{invitationId}/decline/{receiverId}")
    public void declineInvitation(
            @Parameter(description = "ID of the invitation", required = true)
            @PathVariable Long invitationId,
            @Parameter(description = "ID of the receiver", required = true)
            @PathVariable Long receiverId
    ) {
        friendsInvitationService.declineInvitation(invitationId, receiverId);
    }

    @Operation(summary = "Cancel a friend invitation")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Invitation canceled"),
            @ApiResponse(responseCode = "404", description =
                    "Invitation not found / " +
                    "User not found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Error at invitation saving", content = @Content)
    })
    @DeleteMapping("/{invitationId}/cancel/{senderId}")
    public void cancelInvitation(
            @Parameter(description = "ID of the invitation", required = true)
            @PathVariable Long invitationId,
            @Parameter(description = "ID of the sender", required = true)
            @PathVariable Long senderId
    ) {
        friendsInvitationService.cancelInvitation(invitationId, senderId);
    }
}
