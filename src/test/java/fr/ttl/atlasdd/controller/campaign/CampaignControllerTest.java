package fr.ttl.atlasdd.controller.campaign;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.ttl.atlasdd.apidto.campaign.CampaignApiDto;
import fr.ttl.atlasdd.apidto.campaign.CampaignCreateRequestApiDto;
import fr.ttl.atlasdd.apidto.character.ogl5.CharacterSheetApiDto;
import fr.ttl.atlasdd.apidto.user.UserApiDto;
import fr.ttl.atlasdd.apidto.user.UserLightApiDto;
import fr.ttl.atlasdd.exception.GlobalExceptionHandler;
import fr.ttl.atlasdd.exception.campaign.CampaignNotFoundException;
import fr.ttl.atlasdd.exception.campaign.CampaignSavingErrorException;
import fr.ttl.atlasdd.exception.character.CharacterNoteNotFoundException;
import fr.ttl.atlasdd.exception.character.custom.notfound.CustomCharacterNotFoundException;
import fr.ttl.atlasdd.exception.character.ogl5.notfound.Ogl5CharacterNotFoundException;
import fr.ttl.atlasdd.exception.user.UserNotFoundException;
import fr.ttl.atlasdd.service.campaign.CampaignService;
import fr.ttl.atlasdd.sqldto.campaign.CampaignSqlDto;
import fr.ttl.atlasdd.sqldto.character.ogl5.CharacterSheetSqlDto;
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

    @Test
    void addOgl5CharacterToCampaign_Success() throws Exception {
        Long campaignId = 1L;
        Long characterId = 2L;

        CampaignApiDto expectedResponse = new CampaignApiDto();
        expectedResponse.setId(campaignId);
        expectedResponse.setName("Campagne Test");

        when(campaignService.addOgl5CharacterToCampaign(campaignId, characterId))
                .thenReturn(expectedResponse);

        mockMvc.perform(patch("/campaigns/{id}/add-ogl5-character/{characterId}", campaignId, characterId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(campaignId))
                .andExpect(jsonPath("$.name").value("Campagne Test"));
    }

    @Test
    void addOgl5CharacterToCampaign_CampaignNotFound() throws Exception {
        Long nonExistentCampaignId = 999L;
        Long characterId = 1L;

        when(campaignService.addOgl5CharacterToCampaign(nonExistentCampaignId, characterId))
                .thenThrow(new CampaignNotFoundException(ExceptionMessage.CAMPAIGN_NOT_FOUND.getMessage()));

        mockMvc.perform(patch("/campaigns/{id}/add-ogl5-character/{characterId}", nonExistentCampaignId, characterId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value(ExceptionMessage.CAMPAIGN_NOT_FOUND.getMessage()));
    }

    @Test
    void addOgl5CharacterToCampaign_CharacterNotFound() throws Exception {
        Long campaignId = 1L;
        Long nonExistentCharacterId = 999L;

        when(campaignService.addOgl5CharacterToCampaign(campaignId, nonExistentCharacterId))
                .thenThrow(new Ogl5CharacterNotFoundException(ExceptionMessage.CHARACTER_NOT_FOUND.getMessage()));

        mockMvc.perform(patch("/campaigns/{id}/add-ogl5-character/{characterId}", campaignId, nonExistentCharacterId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value(ExceptionMessage.CHARACTER_NOT_FOUND.getMessage()));
    }

    @Test
    void addOgl5CharacterToCampaign_SaveError() throws Exception {
        Long campaignId = 1L;
        Long characterId = 2L;

        when(campaignService.addOgl5CharacterToCampaign(campaignId, characterId))
                .thenThrow(new CampaignSavingErrorException(ExceptionMessage.ADD_CHARACTER_TO_CAMPAIGN_ERROR.getMessage()));

        mockMvc.perform(patch("/campaigns/{id}/add-ogl5-character/{characterId}", campaignId, characterId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError())
                .andExpect(jsonPath("$.message").value(ExceptionMessage.ADD_CHARACTER_TO_CAMPAIGN_ERROR.getMessage()));
    }

    @Test
    void addOgl5CharacterToCampaign_CharacterAlreadyInCampaign() throws Exception {
        Long campaignId = 1L;
        Long characterId = 2L;

        CampaignApiDto existingCampaign = new CampaignApiDto();
        existingCampaign.setId(campaignId);

        when(campaignService.addOgl5CharacterToCampaign(campaignId, characterId))
                .thenReturn(existingCampaign);

        mockMvc.perform(patch("/campaigns/{id}/add-ogl5-character/{characterId}", campaignId, characterId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id" ).value(campaignId));
    }

    @Test
    void removeOgl5CharacterFromCampaign_Success() throws Exception {
        Long campaignId = 1L;
        Long characterId = 2L;

        CampaignApiDto expectedResponse = new CampaignApiDto();
        expectedResponse.setId(campaignId);
        expectedResponse.setName("Campagne Test");

        when(campaignService.removeOgl5CharacterFromCampaign(campaignId, characterId))
                .thenReturn(expectedResponse);

        mockMvc.perform(patch("/campaigns/{id}/remove-ogl5-character/{characterId}", campaignId, characterId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(campaignId))
                .andExpect(jsonPath("$.name").value("Campagne Test"));
    }

    @Test
    void removeOgl5CharacterFromCampaign_CampaignNotFound() throws Exception {
        Long nonExistentCampaignId = 999L;
        Long characterId = 1L;

        when(campaignService.removeOgl5CharacterFromCampaign(nonExistentCampaignId, characterId))
                .thenThrow(new CampaignNotFoundException(ExceptionMessage.CAMPAIGN_NOT_FOUND.getMessage()));

        mockMvc.perform(patch("/campaigns/{id}/remove-ogl5-character/{characterId}", nonExistentCampaignId, characterId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value(ExceptionMessage.CAMPAIGN_NOT_FOUND.getMessage()));
    }

    @Test
    void removeOgl5CharacterFromCampaign_CharacterNotFound() throws Exception {
        Long campaignId = 1L;
        Long nonExistentCharacterId = 999L;

        when(campaignService.removeOgl5CharacterFromCampaign(campaignId, nonExistentCharacterId))
                .thenThrow(new Ogl5CharacterNotFoundException(ExceptionMessage.CHARACTER_NOT_FOUND.getMessage()));

        mockMvc.perform(patch("/campaigns/{id}/remove-ogl5-character/{characterId}", campaignId, nonExistentCharacterId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value(ExceptionMessage.CHARACTER_NOT_FOUND.getMessage()));
    }

    @Test
    void removeOgl5CharacterFromCampaign_SaveError() throws Exception {
        Long campaignId = 1L;
        Long characterId = 2L;

        when(campaignService.removeOgl5CharacterFromCampaign(campaignId, characterId))
                .thenThrow(new CampaignSavingErrorException(ExceptionMessage.REMOVE_CHARACTER_FROM_CAMPAIGN_ERROR.getMessage()));

        mockMvc.perform(patch("/campaigns/{id}/remove-ogl5-character/{characterId}", campaignId, characterId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError())
                .andExpect(jsonPath("$.message").value(ExceptionMessage.REMOVE_CHARACTER_FROM_CAMPAIGN_ERROR.getMessage()));
    }

    @Test
    void removeOgl5CharacterFromCampaign_CharacterNotInCampaign() throws Exception {
        Long campaignId = 1L;
        Long characterId = 2L;

        CampaignApiDto campaignResponse = new CampaignApiDto();
        campaignResponse.setId(campaignId);
        campaignResponse.setName("Campagne Test");

        when(campaignService.removeOgl5CharacterFromCampaign(campaignId, characterId))
                .thenReturn(campaignResponse);

        mockMvc.perform(patch("/campaigns/{id}/remove-ogl5-character/{characterId}", campaignId, characterId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(campaignId))
                .andExpect(jsonPath("$.name").value("Campagne Test"));
    }

    @Test
    void addCustomCharacterToCampaign_Success() throws Exception {
        Long campaignId = 1L;
        Long characterId = 2L;

        CampaignApiDto expectedResponse = new CampaignApiDto();
        expectedResponse.setId(campaignId);
        expectedResponse.setName("Campagne Test");

        when(campaignService.addCustomCharacterToCampaign(campaignId, characterId))
                .thenReturn(expectedResponse);

        mockMvc.perform(patch("/campaigns/{id}/add-custom-character/{characterId}", campaignId, characterId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(campaignId))
                .andExpect(jsonPath("$.name").value("Campagne Test"));
    }

    @Test
    void addCustomCharacterToCampaign_CampaignNotFound() throws Exception {
        Long nonExistentCampaignId = 999L;
        Long characterId = 1L;

        when(campaignService.addCustomCharacterToCampaign(nonExistentCampaignId, characterId))
                .thenThrow(new CampaignNotFoundException(ExceptionMessage.CAMPAIGN_NOT_FOUND.getMessage()));

        mockMvc.perform(patch("/campaigns/{id}/add-custom-character/{characterId}", nonExistentCampaignId, characterId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value(ExceptionMessage.CAMPAIGN_NOT_FOUND.getMessage()));
    }

    @Test
    void addCustomCharacterToCampaign_CharacterNotFound() throws Exception {
        Long campaignId = 1L;
        Long nonExistentCharacterId = 999L;

        when(campaignService.addCustomCharacterToCampaign(campaignId, nonExistentCharacterId))
                .thenThrow(new CustomCharacterNotFoundException(ExceptionMessage.CHARACTER_NOT_FOUND.getMessage()));

        mockMvc.perform(patch("/campaigns/{id}/add-custom-character/{characterId}", campaignId, nonExistentCharacterId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value(ExceptionMessage.CHARACTER_NOT_FOUND.getMessage()));
    }

    @Test
    void addCustomCharacterToCampaign_SaveError() throws Exception {
        Long campaignId = 1L;
        Long characterId = 2L;

        when(campaignService.addCustomCharacterToCampaign(campaignId, characterId))
                .thenThrow(new CampaignSavingErrorException(ExceptionMessage.ADD_CHARACTER_TO_CAMPAIGN_ERROR.getMessage()));

        mockMvc.perform(patch("/campaigns/{id}/add-custom-character/{characterId}", campaignId, characterId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError())
                .andExpect(jsonPath("$.message").value(ExceptionMessage.ADD_CHARACTER_TO_CAMPAIGN_ERROR.getMessage()));
    }

    @Test
    void addCustomCharacterToCampaign_CharacterAlreadyInCampaign() throws Exception {
        Long campaignId = 1L;
        Long characterId = 2L;

        CampaignApiDto existingCampaign = new CampaignApiDto();
        existingCampaign.setId(campaignId);

        when(campaignService.addCustomCharacterToCampaign(campaignId, characterId))
                .thenReturn(existingCampaign);

        mockMvc.perform(patch("/campaigns/{id}/add-custom-character/{characterId}", campaignId, characterId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id" ).value(campaignId));
    }

    @Test
    void removeCustomCharacterFromCampaign_Success() throws Exception {
        Long campaignId = 1L;
        Long characterId = 2L;

        CampaignApiDto expectedResponse = new CampaignApiDto();
        expectedResponse.setId(campaignId);
        expectedResponse.setName("Campagne Test");

        when(campaignService.removeCustomCharacterFromCampaign(campaignId, characterId))
                .thenReturn(expectedResponse);

        mockMvc.perform(patch("/campaigns/{id}/remove-custom-character/{characterId}", campaignId, characterId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(campaignId))
                .andExpect(jsonPath("$.name").value("Campagne Test"));
    }

    @Test
    void removeCustomCharacterFromCampaign_CampaignNotFound() throws Exception {
        Long nonExistentCampaignId = 999L;
        Long characterId = 1L;

        when(campaignService.removeCustomCharacterFromCampaign(nonExistentCampaignId, characterId))
                .thenThrow(new CampaignNotFoundException(ExceptionMessage.CAMPAIGN_NOT_FOUND.getMessage()));

        mockMvc.perform(patch("/campaigns/{id}/remove-custom-character/{characterId}", nonExistentCampaignId, characterId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value(ExceptionMessage.CAMPAIGN_NOT_FOUND.getMessage()));
    }

    @Test
    void removeCustomCharacterFromCampaign_CharacterNotFound() throws Exception {
        Long campaignId = 1L;
        Long nonExistentCharacterId = 999L;

        when(campaignService.removeCustomCharacterFromCampaign(campaignId, nonExistentCharacterId))
                .thenThrow(new CustomCharacterNotFoundException(ExceptionMessage.CHARACTER_NOT_FOUND.getMessage()));

        mockMvc.perform(patch("/campaigns/{id}/remove-custom-character/{characterId}", campaignId, nonExistentCharacterId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value(ExceptionMessage.CHARACTER_NOT_FOUND.getMessage()));
    }

    @Test
    void removeCustomCharacterFromCampaign_SaveError() throws Exception {
        Long campaignId = 1L;
        Long characterId = 2L;

        when(campaignService.removeCustomCharacterFromCampaign(campaignId, characterId))
                .thenThrow(new CampaignSavingErrorException(ExceptionMessage.REMOVE_CHARACTER_FROM_CAMPAIGN_ERROR.getMessage()));

        mockMvc.perform(patch("/campaigns/{id}/remove-custom-character/{characterId}", campaignId, characterId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError())
                .andExpect(jsonPath("$.message").value(ExceptionMessage.REMOVE_CHARACTER_FROM_CAMPAIGN_ERROR.getMessage()));
    }

    @Test
    void removeCustomCharacterFromCampaign_CharacterNotInCampaign() throws Exception {
        Long campaignId = 1L;
        Long characterId = 2L;

        CampaignApiDto campaignResponse = new CampaignApiDto();
        campaignResponse.setId(campaignId);
        campaignResponse.setName("Campagne Test");

        when(campaignService.removeCustomCharacterFromCampaign(campaignId, characterId))
                .thenReturn(campaignResponse);

        mockMvc.perform(patch("/campaigns/{id}/remove-custom-character/{characterId}", campaignId, characterId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(campaignId))
                .andExpect(jsonPath("$.name").value("Campagne Test"));
    }

    @Test
    void deleteCampaign_Success() throws Exception {
        Long campaignId = 1L;
        doNothing().when(campaignService).deleteCampaign(campaignId);

        mockMvc.perform(delete("/campaigns/{id}", campaignId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void deleteCampaign_DeleteError() throws Exception {
        Long campaignId = 1L;
        doThrow(new CampaignSavingErrorException(ExceptionMessage.CAMPAIGN_DELETE_ERROR.getMessage()))
                .when(campaignService).deleteCampaign(campaignId);

        mockMvc.perform(delete("/campaigns/{id}", campaignId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError())
                .andExpect(jsonPath("$.message").value(ExceptionMessage.CAMPAIGN_DELETE_ERROR.getMessage()));
    }

    private String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
