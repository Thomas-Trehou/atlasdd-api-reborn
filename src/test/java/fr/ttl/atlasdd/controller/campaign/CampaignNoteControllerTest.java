package fr.ttl.atlasdd.controller.campaign;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.ttl.atlasdd.apidto.campaign.CampaignNoteApiDto;
import fr.ttl.atlasdd.exception.GlobalExceptionHandler;
import fr.ttl.atlasdd.exception.campaign.CampaignNotFoundException;
import fr.ttl.atlasdd.exception.campaign.CampaignNoteNotFoundException;
import fr.ttl.atlasdd.exception.campaign.CampaignNoteSavingErrorException;
import fr.ttl.atlasdd.exception.user.UserNotFoundException;
import fr.ttl.atlasdd.service.campaign.CampaignNoteService;
import fr.ttl.atlasdd.utils.exception.ExceptionMessage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class CampaignNoteControllerTest {

    private MockMvc mockMvc;

    @Mock
    private CampaignNoteService campaignNoteService;

    @InjectMocks
    private CampaignNoteController campaignNoteController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(campaignNoteController)
                .setControllerAdvice(new GlobalExceptionHandler())
                .build();
    }

    @Test
    void createCampaignNote_Success() throws Exception {
        Long campaignId = 1L;
        Long userId = 2L;
        CampaignNoteApiDto requestDto = new CampaignNoteApiDto();
        requestDto.setContent("Test note");

        CampaignNoteApiDto responseDto = new CampaignNoteApiDto();
        responseDto.setId(1L);
        responseDto.setContent("Test note");

        when(campaignNoteService.createCampaignNote(campaignId, userId, requestDto))
                .thenReturn(responseDto);

        mockMvc.perform(post("/campaign-notes/campaigns/{campaignId}/users/{userId}", campaignId, userId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(requestDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.content").value("Test note"));
    }

    @Test
    void createCampaignNote_CampaignNotFound() throws Exception {
        Long nonExistentCampaignId = 999L;
        Long userId = 2L;
        CampaignNoteApiDto requestDto = new CampaignNoteApiDto();
        requestDto.setContent("Test note");

        when(campaignNoteService.createCampaignNote(nonExistentCampaignId, userId, requestDto))
                .thenThrow(new CampaignNotFoundException(ExceptionMessage.CAMPAIGN_NOT_FOUND.getMessage()));

        mockMvc.perform(post("/campaign-notes/campaigns/{campaignId}/users/{userId}", nonExistentCampaignId, userId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(requestDto)))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value(ExceptionMessage.CAMPAIGN_NOT_FOUND.getMessage()));
    }

    @Test
    void createCampaignNote_UserNotFound() throws Exception {
        Long campaignId = 1L;
        Long nonExistentUserId = 999L;
        CampaignNoteApiDto requestDto = new CampaignNoteApiDto();
        requestDto.setTitle("Test title");
        requestDto.setContent("Test note");

        when(campaignNoteService.createCampaignNote(campaignId, nonExistentUserId, requestDto))
                .thenThrow(new UserNotFoundException(ExceptionMessage.USER_NOT_FOUND.getMessage()));

        mockMvc.perform(post("/campaign-notes/campaigns/{campaignId}/users/{userId}", campaignId, nonExistentUserId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(requestDto)))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value(ExceptionMessage.USER_NOT_FOUND.getMessage()));
    }

    @Test
    void createCampaignNote_SaveError() throws Exception {
        Long campaignId = 1L;
        Long userId = 2L;
        CampaignNoteApiDto requestDto = new CampaignNoteApiDto();
        requestDto.setTitle("Test title");
        requestDto.setContent("Test note");

        when(campaignNoteService.createCampaignNote(campaignId, userId, requestDto))
                .thenThrow(new CampaignNoteSavingErrorException(ExceptionMessage.CAMPAIGN_NOTE_SAVE_ERROR.getMessage()));

        mockMvc.perform(post("/campaign-notes/campaigns/{campaignId}/users/{userId}", campaignId, userId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(requestDto)))
                .andExpect(status().isInternalServerError())
                .andExpect(jsonPath("$.message").value(ExceptionMessage.CAMPAIGN_NOTE_SAVE_ERROR.getMessage()));
    }

    @Test
    void getCampaignNoteById_Success() throws Exception {
        Long noteId = 1L;
        CampaignNoteApiDto responseDto = new CampaignNoteApiDto();
        responseDto.setId(noteId);
        responseDto.setTitle("Test title");
        responseDto.setContent("Test note");

        when(campaignNoteService.getCampaignNoteById(noteId)).thenReturn(responseDto);

        mockMvc.perform(get("/campaign-notes/{noteId}", noteId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(noteId))
                .andExpect(jsonPath("$.title").value("Test title"))
                .andExpect(jsonPath("$.content").value("Test note"));
    }

    @Test
    void getCampaignNoteById_NoteNotFound() throws Exception {
        Long nonExistentNoteId = 999L;

        when(campaignNoteService.getCampaignNoteById(nonExistentNoteId))
                .thenThrow(new CampaignNoteNotFoundException(ExceptionMessage.CAMPAIGN_NOTE_NOT_FOUND.getMessage()));

        mockMvc.perform(get("/campaign-notes/{noteId}", nonExistentNoteId))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value(ExceptionMessage.CAMPAIGN_NOTE_NOT_FOUND.getMessage()));
    }




    private String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
