package fr.ttl.atlasdd.controller.user;

import fr.ttl.atlasdd.exception.GlobalExceptionHandler;
import fr.ttl.atlasdd.exception.user.*;
import fr.ttl.atlasdd.service.user.FriendsInvitationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class FriendsInvitationControllerTest {

    private MockMvc mockMvc;

    @Mock
    private FriendsInvitationService friendsInvitationService;

    @InjectMocks
    private FriendsInvitationController friendsInvitationController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(friendsInvitationController)
                .setControllerAdvice(new GlobalExceptionHandler())
                .build();
    }

    @Test
    void sendInvitation_Success() throws Exception {
        Long senderId = 1L;
        Long receiverId = 2L;

        doNothing().when(friendsInvitationService).sendInvitation(senderId, receiverId);

        mockMvc.perform(post("/friends-invitations/{senderId}/to/{receiverId}", senderId, receiverId))
                .andExpect(status().isOk());

        verify(friendsInvitationService).sendInvitation(senderId, receiverId);
    }

    @Test
    void sendInvitation_UserNotFound() throws Exception {
        Long senderId = 1L;
        Long receiverId = 2L;

        doThrow(new UserNotFoundException("User not found"))
                .when(friendsInvitationService).sendInvitation(senderId, receiverId);

        mockMvc.perform(post("/friends-invitations/{senderId}/to/{receiverId}", senderId, receiverId))
                .andExpect(status().isNotFound());
    }

    @Test
    void acceptInvitation_Success() throws Exception {
        Long invitationId = 1L;
        Long receiverId = 2L;

        doNothing().when(friendsInvitationService).acceptInvitation(invitationId, receiverId);

        mockMvc.perform(patch("/friends-invitations/{invitationId}/accept/{receiverId}", invitationId, receiverId))
                .andExpect(status().isOk());

        verify(friendsInvitationService).acceptInvitation(invitationId, receiverId);
    }

    @Test
    void acceptInvitation_InvitationNotFound() throws Exception {
        Long invitationId = 1L;
        Long receiverId = 2L;

        doThrow(new FriendsInvitationNotFoundException("Invitation not found"))
                .when(friendsInvitationService).acceptInvitation(invitationId, receiverId);

        mockMvc.perform(patch("/friends-invitations/{invitationId}/accept/{receiverId}", invitationId, receiverId))
                .andExpect(status().isNotFound());
    }

    @Test
    void declineInvitation_Success() throws Exception {
        Long invitationId = 1L;
        Long receiverId = 2L;

        doNothing().when(friendsInvitationService).declineInvitation(invitationId, receiverId);

        mockMvc.perform(delete("/friends-invitations/{invitationId}/decline/{receiverId}", invitationId, receiverId))
                .andExpect(status().isOk());

        verify(friendsInvitationService).declineInvitation(invitationId, receiverId);
    }

    @Test
    void declineInvitation_InvitationNotFound() throws Exception {
        Long invitationId = 1L;
        Long receiverId = 2L;

        doThrow(new FriendsInvitationNotFoundException("Invitation not found"))
                .when(friendsInvitationService).declineInvitation(invitationId, receiverId);

        mockMvc.perform(delete("/friends-invitations/{invitationId}/decline/{receiverId}", invitationId, receiverId))
                .andExpect(status().isNotFound());
    }

    @Test
    void cancelInvitation_Success() throws Exception {
        Long invitationId = 1L;
        Long senderId = 2L;

        doNothing().when(friendsInvitationService).cancelInvitation(invitationId, senderId);

        mockMvc.perform(delete("/friends-invitations/{invitationId}/cancel/{senderId}", invitationId, senderId))
                .andExpect(status().isOk());

        verify(friendsInvitationService).cancelInvitation(invitationId, senderId);
    }

    @Test
    void cancelInvitation_InvitationNotFound() throws Exception {
        Long invitationId = 1L;
        Long senderId = 2L;

        doThrow(new FriendsInvitationNotFoundException("Invitation not found"))
                .when(friendsInvitationService).cancelInvitation(invitationId, senderId);

        mockMvc.perform(delete("/friends-invitations/{invitationId}/cancel/{senderId}", invitationId, senderId))
                .andExpect(status().isNotFound());
    }
}