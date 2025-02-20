package fr.ttl.atlasdd.controller.character;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.ttl.atlasdd.apidto.character.NoteCharacterApiDto;
import fr.ttl.atlasdd.exception.GlobalExceptionHandler;
import fr.ttl.atlasdd.exception.character.CharacterNoteSavingErrorException;
import fr.ttl.atlasdd.exception.character.ogl5.notfound.Ogl5CharacterNotFoundException;
import fr.ttl.atlasdd.exception.character.ogl5.savingerror.Ogl5CharacterSavingErrorException;
import fr.ttl.atlasdd.service.character.NoteCharacterService;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class CharacterNoteControllerTest {

    private MockMvc mockMvc;

    @Mock
    private NoteCharacterService noteCharacterService;

    @InjectMocks
    private NoteCharacterController noteCharacterController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(noteCharacterController)
                .setControllerAdvice(new GlobalExceptionHandler())
                .build();
    }

    private final Long TEST_CHARACTER_ID = 1L;
    private final String TEST_NOTE_TITLE = "Test note title";
    private final String TEST_NOTE_CONTENT = "Test note content";

    @Test
    void addNoteToOgl5Character_Success() throws Exception {
        Long characterId = TEST_CHARACTER_ID;
        NoteCharacterApiDto noteCharacterApiDto = new NoteCharacterApiDto();
        noteCharacterApiDto.setTitle(TEST_NOTE_TITLE);
        noteCharacterApiDto.setContent(TEST_NOTE_CONTENT);

        when(noteCharacterService.createOgl5CharacterNote(characterId, noteCharacterApiDto))
                .thenReturn(noteCharacterApiDto);

        mockMvc.perform(post("/character-notes/ogl5/characters/{characterId}", characterId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(noteCharacterApiDto)))
                            .andExpect(status().isOk())
                            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                            .andExpect(jsonPath("$.title").value("Test note title"))
                            .andExpect(jsonPath("$.content").value("Test note content"));


    }

    @Test
    void addNoteToOgl5Character_NotFound() throws Exception {
        Long characterId = TEST_CHARACTER_ID;
        NoteCharacterApiDto noteCharacterApiDto = new NoteCharacterApiDto();
        noteCharacterApiDto.setTitle(TEST_NOTE_TITLE);
        noteCharacterApiDto.setContent(TEST_NOTE_CONTENT);

        when(noteCharacterService.createOgl5CharacterNote(characterId, noteCharacterApiDto))
                .thenThrow(new Ogl5CharacterNotFoundException(ExceptionMessage.CHARACTER_NOT_FOUND.getMessage()));

        mockMvc.perform(post("/character-notes/ogl5/characters/{characterId}", characterId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(noteCharacterApiDto)))
                            .andExpect(status().isNotFound())
                            .andExpect(jsonPath("$.message").value(ExceptionMessage.CHARACTER_NOT_FOUND.getMessage()));
    }

    @Test
    void addNoteToOgl5Character_NoteSaveError() throws Exception {
        Long characterId = TEST_CHARACTER_ID;
        NoteCharacterApiDto noteCharacterApiDto = new NoteCharacterApiDto();
        noteCharacterApiDto.setTitle(TEST_NOTE_TITLE);
        noteCharacterApiDto.setContent(TEST_NOTE_CONTENT);

        when(noteCharacterService.createOgl5CharacterNote(characterId, noteCharacterApiDto))
                .thenThrow(new CharacterNoteSavingErrorException(ExceptionMessage.CHARACTER_NOTE_SAVE_ERROR.getMessage()));

        mockMvc.perform(post("/character-notes/ogl5/characters/{characterId}", characterId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(noteCharacterApiDto)))
                            .andExpect(status().isInternalServerError())
                            .andExpect(jsonPath("$.message").value(ExceptionMessage.CHARACTER_NOTE_SAVE_ERROR.getMessage()));
    }

    @Test
    void addNoteToOgl5Character_CharacterUpdateError() throws Exception {
        Long characterId = TEST_CHARACTER_ID;
        NoteCharacterApiDto noteCharacterApiDto = new NoteCharacterApiDto();
        noteCharacterApiDto.setTitle(TEST_NOTE_TITLE);
        noteCharacterApiDto.setContent(TEST_NOTE_CONTENT);

        when(noteCharacterService.createOgl5CharacterNote(characterId, noteCharacterApiDto))
                .thenThrow(new Ogl5CharacterSavingErrorException(ExceptionMessage.CHARACTER_UPDATE_ERROR.getMessage()));

        mockMvc.perform(post("/character-notes/ogl5/characters/{characterId}", characterId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(noteCharacterApiDto)))
                            .andExpect(status().isInternalServerError())
                            .andExpect(jsonPath("$.message").value(ExceptionMessage.CHARACTER_UPDATE_ERROR.getMessage()));
    }

    private String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
