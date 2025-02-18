package fr.ttl.atlasdd.controller.campaign;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.ttl.atlasdd.apidto.campaign.CampaignApiDto;
import fr.ttl.atlasdd.apidto.campaign.CampaignCreateRequestApiDto;
import fr.ttl.atlasdd.apidto.user.UserApiDto;
import fr.ttl.atlasdd.apidto.user.UserLightApiDto;
import fr.ttl.atlasdd.exception.GlobalExceptionHandler;
import fr.ttl.atlasdd.exception.campaign.CampaignNotFoundException;
import fr.ttl.atlasdd.exception.campaign.CampaignSavingErrorException;
import fr.ttl.atlasdd.exception.user.UserNotFoundException;
import fr.ttl.atlasdd.service.campaign.CampaignService;
import fr.ttl.atlasdd.sqldto.campaign.CampaignSqlDto;
import fr.ttl.atlasdd.sqldto.user.UserSqlDto;
import fr.ttl.atlasdd.utils.exception.ExceptionMessage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class CampaignControllerTest {

    private MockMvc mockMvc;

    @Mock
    private CampaignService campaignService;

    @InjectMocks
    private CampaignController campaignController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(campaignController)
                .setControllerAdvice(new GlobalExceptionHandler())
                .build();
    }

    @Test
    void createCampaign_Success() throws Exception {

        CampaignCreateRequestApiDto requestDto = new CampaignCreateRequestApiDto();
        requestDto.setName("Campaign");
        requestDto.setDescription("Description");

        CampaignApiDto expectedResponse = new CampaignApiDto();
        expectedResponse.setName("Campaign");
        expectedResponse.setDescription("Description");

        when(campaignService.createCampaign(any(CampaignCreateRequestApiDto.class)))
                .thenReturn(expectedResponse);

        mockMvc.perform(post("/campaigns")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(requestDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Campaign"))
                .andExpect(jsonPath("$.description").value("Description"));
    }

    @Test
    void createCampaign_UserNotFound() throws Exception {
        CampaignCreateRequestApiDto requestDto = new CampaignCreateRequestApiDto();
        requestDto.setName("Campaign");
        requestDto.setDescription("Description");

        when(campaignService.createCampaign(any(CampaignCreateRequestApiDto.class)))
                .thenThrow(new UserNotFoundException(ExceptionMessage.USER_NOT_FOUND.getMessage()));

        mockMvc.perform(post("/campaigns")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(requestDto)))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value(ExceptionMessage.USER_NOT_FOUND.getMessage()));
    }

    @Test
    void createCampaign_CampaignSavingError() throws Exception {
        CampaignCreateRequestApiDto requestDto = new CampaignCreateRequestApiDto();
        requestDto.setName("Campaign");
        requestDto.setDescription("Description");

        when(campaignService.createCampaign(any(CampaignCreateRequestApiDto.class)))
                .thenThrow(new CampaignSavingErrorException(ExceptionMessage.CAMPAIGN_SAVE_ERROR.getMessage()));

        mockMvc.perform(post("/campaigns")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(requestDto)))
                .andExpect(status().isInternalServerError())
                .andExpect(jsonPath("$.message").value(ExceptionMessage.CAMPAIGN_SAVE_ERROR.getMessage()));
    }

    @Test
    void getCampaigns_Success() throws Exception {
        CampaignApiDto campaignApiDto = new CampaignApiDto();
        campaignApiDto.setId(1L);
        campaignApiDto.setName("Campaign");
        campaignApiDto.setDescription("Description");

        when(campaignService.getCampaignById(1L)).thenReturn(campaignApiDto);

        mockMvc.perform(get("/campaigns/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value("Campaign"))
                .andExpect(jsonPath("$.description").value("Description"));

        verify(campaignService, times(1)).getCampaignById(1L);
    }

    @Test
    void getCampaigns_NotFound() throws Exception {
        when(campaignService.getCampaignById(1L))
                .thenThrow(new UserNotFoundException(ExceptionMessage.CAMPAIGN_NOT_FOUND.getMessage()));

        mockMvc.perform(get("/campaigns/{id}", 1L))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value(ExceptionMessage.CAMPAIGN_NOT_FOUND.getMessage()));

        verify(campaignService, times(1)).getCampaignById(1L);
    }

    @Test
    void updateCampaign_Success() throws Exception {
        Long campaignId = 1L;
        CampaignCreateRequestApiDto requestDto = new CampaignCreateRequestApiDto();
        requestDto.setName("Campagne Mise à Jour");
        requestDto.setDescription("Nouvelle Description");
        requestDto.setGameMasterId(1L);

        CampaignApiDto expectedResponse = new CampaignApiDto();
        expectedResponse.setId(campaignId);
        expectedResponse.setName("Campagne Mise à Jour");
        expectedResponse.setDescription("Nouvelle Description");

        when(campaignService.updateCampaign(eq(campaignId), any(CampaignCreateRequestApiDto.class)))
                .thenReturn(expectedResponse);

        mockMvc.perform(patch("/campaigns/{id}", campaignId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(requestDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(campaignId))
                .andExpect(jsonPath("$.name").value("Campagne Mise à Jour"))
                .andExpect(jsonPath("$.description").value("Nouvelle Description"));
    }

    @Test
    void updateCampaign_NotFound() throws Exception {
        Long nonExistentId = 999L;
        CampaignCreateRequestApiDto requestDto = new CampaignCreateRequestApiDto();
        requestDto.setName("Test");

        when(campaignService.updateCampaign(eq(nonExistentId), any(CampaignCreateRequestApiDto.class)))
                .thenThrow(new CampaignNotFoundException("Campaign not found"));

        mockMvc.perform(patch("/campaigns/{id}", nonExistentId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(requestDto)))
                .andExpect(status().isNotFound());
    }

    @Test
    void updateCampaign_InvalidGameMaster() throws Exception {
        Long campaignId = 1L;
        CampaignCreateRequestApiDto requestDto = new CampaignCreateRequestApiDto();
        requestDto.setGameMasterId(999L);

        when(campaignService.updateCampaign(eq(campaignId), any(CampaignCreateRequestApiDto.class)))
                .thenThrow(new UserNotFoundException("User not found"));

        mockMvc.perform(patch("/campaigns/{id}", campaignId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(requestDto)))
                .andExpect(status().isNotFound());
    }

    @Test
    void updateCampaign_SaveError() throws Exception {
        Long campaignId = 1L;
        CampaignCreateRequestApiDto requestDto = new CampaignCreateRequestApiDto();

        when(campaignService.updateCampaign(eq(campaignId), any(CampaignCreateRequestApiDto.class)))
                .thenThrow(new CampaignSavingErrorException("Error saving campaign"));

        mockMvc.perform(patch("/campaigns/{id}", campaignId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(requestDto)))
                .andExpect(status().isInternalServerError());
    }

    @Test
    void addPlayerToCampaign_Success() throws Exception {
        Long campaignId = 1L;
        Long playerId = 2L;

        CampaignApiDto expectedResponse = new CampaignApiDto();
        expectedResponse.setId(campaignId);
        expectedResponse.setName("Campagne Test");
        List<UserLightApiDto> players = new ArrayList<>();
        UserLightApiDto player = new UserLightApiDto();
        player.setId(playerId);
        players.add(player);
        expectedResponse.setCampaignPlayers(players);

        when(campaignService.addPlayerToCampaign(campaignId, playerId))
                .thenReturn(expectedResponse);

        mockMvc.perform(patch("/campaigns/{id}/add-player/{playerId}", campaignId, playerId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(campaignId))
                .andExpect(jsonPath("$.campaignPlayers[0].id").value(playerId));
    }

    @Test
    void addPlayerToCampaign_CampaignNotFound() throws Exception {
        Long nonExistentCampaignId = 999L;
        Long playerId = 1L;

        when(campaignService.addPlayerToCampaign(nonExistentCampaignId, playerId))
                .thenThrow(new CampaignNotFoundException(ExceptionMessage.CAMPAIGN_NOT_FOUND.getMessage()));

        mockMvc.perform(patch("/campaigns/{id}/add-player/{playerId}", nonExistentCampaignId, playerId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value(ExceptionMessage.CAMPAIGN_NOT_FOUND.getMessage()));
    }

    @Test
    void addPlayerToCampaign_PlayerNotFound() throws Exception {
        Long campaignId = 1L;
        Long nonExistentPlayerId = 999L;

        when(campaignService.addPlayerToCampaign(campaignId, nonExistentPlayerId))
                .thenThrow(new UserNotFoundException(ExceptionMessage.USER_NOT_FOUND.getMessage()));

        mockMvc.perform(patch("/campaigns/{id}/add-player/{playerId}", campaignId, nonExistentPlayerId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value(ExceptionMessage.USER_NOT_FOUND.getMessage()));
    }

    @Test
    void addPlayerToCampaign_PlayerAlreadyInCampaign() throws Exception {
        Long campaignId = 1L;
        Long playerId = 2L;

        CampaignApiDto existingCampaign = new CampaignApiDto();
        existingCampaign.setId(campaignId);
        List<UserLightApiDto> players = new ArrayList<>();
        UserLightApiDto player = new UserLightApiDto();
        player.setId(playerId);
        players.add(player);
        existingCampaign.setCampaignPlayers(players);

        when(campaignService.addPlayerToCampaign(campaignId, playerId))
                .thenReturn(existingCampaign);

        mockMvc.perform(patch("/campaigns/{id}/add-player/{playerId}", campaignId, playerId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.campaignPlayers[0].id").value(playerId));
    }

    @Test
    void addPlayerToCampaign_SaveError() throws Exception {
        Long campaignId = 1L;
        Long playerId = 2L;

        when(campaignService.addPlayerToCampaign(campaignId, playerId))
                .thenThrow(new CampaignSavingErrorException(ExceptionMessage.CAMPAIGN_SAVE_ERROR.getMessage()));

        mockMvc.perform(patch("/campaigns/{id}/add-player/{playerId}", campaignId, playerId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError())
                .andExpect(jsonPath("$.message").value(ExceptionMessage.CAMPAIGN_SAVE_ERROR.getMessage()));
    }

    @Test
    void removePlayerFromCampaign_Success() throws Exception {
        Long campaignId = 1L;
        Long playerId = 2L;

        CampaignApiDto expectedResponse = new CampaignApiDto();
        expectedResponse.setId(campaignId);
        expectedResponse.setName("Campagne Test");
        expectedResponse.setCampaignPlayers(new ArrayList<>());

        when(campaignService.removePlayerFromCampaign(campaignId, playerId))
                .thenReturn(expectedResponse);

        mockMvc.perform(patch("/campaigns/{id}/remove-player/{playerId}", campaignId, playerId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(campaignId))
                .andExpect(jsonPath("$.campaignPlayers").isArray())
                .andExpect(jsonPath("$.campaignPlayers").isEmpty());
    }

    @Test
    void removePlayerFromCampaign_CampaignNotFound() throws Exception {
        // Arrange
        Long nonExistentCampaignId = 999L;
        Long playerId = 1L;

        when(campaignService.removePlayerFromCampaign(nonExistentCampaignId, playerId))
                .thenThrow(new CampaignNotFoundException(ExceptionMessage.CAMPAIGN_NOT_FOUND.getMessage()));

        mockMvc.perform(patch("/campaigns/{id}/remove-player/{playerId}", nonExistentCampaignId, playerId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value(ExceptionMessage.CAMPAIGN_NOT_FOUND.getMessage()));
    }

    @Test
    void removePlayerFromCampaign_PlayerNotFound() throws Exception {
        Long campaignId = 1L;
        Long nonExistentPlayerId = 999L;

        when(campaignService.removePlayerFromCampaign(campaignId, nonExistentPlayerId))
                .thenThrow(new UserNotFoundException(ExceptionMessage.USER_NOT_FOUND.getMessage()));

        mockMvc.perform(patch("/campaigns/{id}/remove-player/{playerId}", campaignId, nonExistentPlayerId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value(ExceptionMessage.USER_NOT_FOUND.getMessage()));
    }

    @Test
    void removePlayerFromCampaign_PlayerNotInCampaign() throws Exception {
        Long campaignId = 1L;
        Long playerId = 2L;

        CampaignApiDto expectedResponse = new CampaignApiDto();
        expectedResponse.setId(campaignId);
        expectedResponse.setCampaignPlayers(new ArrayList<>());

        when(campaignService.removePlayerFromCampaign(campaignId, playerId))
                .thenReturn(expectedResponse);

        mockMvc.perform(patch("/campaigns/{id}/remove-player/{playerId}", campaignId, playerId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.campaignPlayers").isEmpty());
    }

    @Test
    void removePlayerFromCampaign_SaveError() throws Exception {
        Long campaignId = 1L;
        Long playerId = 2L;

        when(campaignService.removePlayerFromCampaign(campaignId, playerId))
                .thenThrow(new CampaignSavingErrorException(ExceptionMessage.CAMPAIGN_SAVE_ERROR.getMessage()));

        mockMvc.perform(patch("/campaigns/{id}/remove-player/{playerId}", campaignId, playerId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError())
                .andExpect(jsonPath("$.message").value(ExceptionMessage.CAMPAIGN_SAVE_ERROR.getMessage()));
    }

    private String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
