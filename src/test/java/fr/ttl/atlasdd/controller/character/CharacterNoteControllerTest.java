package fr.ttl.atlasdd.controller.character;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.ttl.atlasdd.apidto.character.NoteCharacterApiDto;
import fr.ttl.atlasdd.exception.GlobalExceptionHandler;
import fr.ttl.atlasdd.exception.character.CharacterNoteNotFoundException;
import fr.ttl.atlasdd.exception.character.CharacterNoteSavingErrorException;
import fr.ttl.atlasdd.exception.character.custom.notfound.CustomCharacterNotFoundException;
import fr.ttl.atlasdd.exception.character.custom.savingerror.CustomCharacterSavingErrorException;
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

import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class CharacterNoteControllerTest {

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
    private final Long TEST_NOTE_ID = 1L;
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

    @Test
    void getNotesByOgl5CharacterId_Success() throws Exception {
        Long characterId = TEST_CHARACTER_ID;
        List<NoteCharacterApiDto> noteCharacterApiDtos = List.of(
                new NoteCharacterApiDto("Test note title 1", "Test note content 1"),
                new NoteCharacterApiDto("Test note title 2", "Test note content 2")
        );

        when(noteCharacterService.getNotesByOgl5CharacterId(characterId))
                .thenReturn(noteCharacterApiDtos);

        mockMvc.perform(get("/character-notes/ogl5/characters/{characterId}", characterId))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].title").value("Test note title 1"))
                .andExpect(jsonPath("$[0].content").value("Test note content 1"))
                .andExpect(jsonPath("$[1].title").value("Test note title 2"))
                .andExpect(jsonPath("$[1].content").value("Test note content 2"));
    }

    @Test
    void getNotesByOgl5CharacterId_EmptyList() throws Exception {
        Long characterId = TEST_CHARACTER_ID;

        when(noteCharacterService.getNotesByOgl5CharacterId(characterId))
                .thenReturn(Collections.emptyList());

        mockMvc.perform(get("/character-notes/ogl5/characters/{characterId}", characterId))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$").isEmpty());
    }

    @Test
    void getNotesByOgl5CharacterId_CharacterNotFound() throws Exception {
        Long characterId = TEST_CHARACTER_ID;

        when(noteCharacterService.getNotesByOgl5CharacterId(characterId))
                .thenThrow(new Ogl5CharacterNotFoundException(ExceptionMessage.CHARACTER_NOT_FOUND.getMessage()));

        mockMvc.perform(get("/character-notes/ogl5/characters/{characterId}", characterId))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value(ExceptionMessage.CHARACTER_NOT_FOUND.getMessage()));
    }

    @Test
    void addNoteToCustomCharacter_Success() throws Exception {
        Long characterId = TEST_CHARACTER_ID;
        NoteCharacterApiDto noteCharacterApiDto = new NoteCharacterApiDto();
        noteCharacterApiDto.setTitle(TEST_NOTE_TITLE);
        noteCharacterApiDto.setContent(TEST_NOTE_CONTENT);

        when(noteCharacterService.createCustomCharacterNote(characterId, noteCharacterApiDto))
                .thenReturn(noteCharacterApiDto);

        mockMvc.perform(post("/character-notes/custom/characters/{characterId}", characterId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(noteCharacterApiDto)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.title").value("Test note title"))
                .andExpect(jsonPath("$.content").value("Test note content"));


    }

    @Test
    void addNoteToCustomCharacter_NotFound() throws Exception {
        Long characterId = TEST_CHARACTER_ID;
        NoteCharacterApiDto noteCharacterApiDto = new NoteCharacterApiDto();
        noteCharacterApiDto.setTitle(TEST_NOTE_TITLE);
        noteCharacterApiDto.setContent(TEST_NOTE_CONTENT);

        when(noteCharacterService.createCustomCharacterNote(characterId, noteCharacterApiDto))
                .thenThrow(new CustomCharacterNotFoundException(ExceptionMessage.CHARACTER_NOT_FOUND.getMessage()));

        mockMvc.perform(post("/character-notes/custom/characters/{characterId}", characterId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(noteCharacterApiDto)))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value(ExceptionMessage.CHARACTER_NOT_FOUND.getMessage()));
    }

    @Test
    void addNoteToCustomCharacter_NoteSaveError() throws Exception {
        Long characterId = TEST_CHARACTER_ID;
        NoteCharacterApiDto noteCharacterApiDto = new NoteCharacterApiDto();
        noteCharacterApiDto.setTitle(TEST_NOTE_TITLE);
        noteCharacterApiDto.setContent(TEST_NOTE_CONTENT);

        when(noteCharacterService.createCustomCharacterNote(characterId, noteCharacterApiDto))
                .thenThrow(new CharacterNoteSavingErrorException(ExceptionMessage.CHARACTER_NOTE_SAVE_ERROR.getMessage()));

        mockMvc.perform(post("/character-notes/custom/characters/{characterId}", characterId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(noteCharacterApiDto)))
                .andExpect(status().isInternalServerError())
                .andExpect(jsonPath("$.message").value(ExceptionMessage.CHARACTER_NOTE_SAVE_ERROR.getMessage()));
    }

    @Test
    void addNoteToCustomCharacter_CharacterUpdateError() throws Exception {
        Long characterId = TEST_CHARACTER_ID;
        NoteCharacterApiDto noteCharacterApiDto = new NoteCharacterApiDto();
        noteCharacterApiDto.setTitle(TEST_NOTE_TITLE);
        noteCharacterApiDto.setContent(TEST_NOTE_CONTENT);

        when(noteCharacterService.createCustomCharacterNote(characterId, noteCharacterApiDto))
                .thenThrow(new CustomCharacterSavingErrorException(ExceptionMessage.CHARACTER_UPDATE_ERROR.getMessage()));

        mockMvc.perform(post("/character-notes/custom/characters/{characterId}", characterId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(noteCharacterApiDto)))
                .andExpect(status().isInternalServerError())
                .andExpect(jsonPath("$.message").value(ExceptionMessage.CHARACTER_UPDATE_ERROR.getMessage()));
    }

    @Test
    void getNotesByCustomCharacterId_Success() throws Exception {
        Long characterId = TEST_CHARACTER_ID;
        List<NoteCharacterApiDto> noteCharacterApiDtos = List.of(
                new NoteCharacterApiDto("Test note title 1", "Test note content 1"),
                new NoteCharacterApiDto("Test note title 2", "Test note content 2")
        );

        when(noteCharacterService.getNotesByCustomCharacterId(characterId))
                .thenReturn(noteCharacterApiDtos);

        mockMvc.perform(get("/character-notes/custom/characters/{characterId}", characterId))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].title").value("Test note title 1"))
                .andExpect(jsonPath("$[0].content").value("Test note content 1"))
                .andExpect(jsonPath("$[1].title").value("Test note title 2"))
                .andExpect(jsonPath("$[1].content").value("Test note content 2"));
    }

    @Test
    void getNotesByCustomCharacterId_EmptyList() throws Exception {
        Long characterId = TEST_CHARACTER_ID;

        when(noteCharacterService.getNotesByCustomCharacterId(characterId))
                .thenReturn(Collections.emptyList());

        mockMvc.perform(get("/character-notes/custom/characters/{characterId}", characterId))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$").isEmpty());
    }

    @Test
    void getNotesByCustomCharacterId_CharacterNotFound() throws Exception {
        Long characterId = TEST_CHARACTER_ID;

        when(noteCharacterService.getNotesByCustomCharacterId(characterId))
                .thenThrow(new CustomCharacterNotFoundException(ExceptionMessage.CHARACTER_NOT_FOUND.getMessage()));

        mockMvc.perform(get("/character-notes/custom/characters/{characterId}", characterId))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value(ExceptionMessage.CHARACTER_NOT_FOUND.getMessage()));
    }

    @Test
    void updateNote_Success() throws Exception {
        Long noteId = TEST_NOTE_ID;
        NoteCharacterApiDto noteCharacterApiDto = new NoteCharacterApiDto();
        noteCharacterApiDto.setTitle(TEST_NOTE_TITLE);
        noteCharacterApiDto.setContent(TEST_NOTE_CONTENT);
        NoteCharacterApiDto updatedNote = new NoteCharacterApiDto(TEST_NOTE_TITLE, TEST_NOTE_CONTENT);
        updatedNote.setContent("Updated note content");

        when(noteCharacterService.updateNote(noteId, noteCharacterApiDto))
                .thenReturn(updatedNote);

        mockMvc.perform(put("/character-notes/{noteId}", noteId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(noteCharacterApiDto)))
                            .andExpect(status().isOk())
                            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                            .andExpect(jsonPath("$.title").value(TEST_NOTE_TITLE))
                            .andExpect(jsonPath("$.content").value("Updated note content"));
    }

    @Test
    void updateNote_NoteNotFound() throws Exception {
        Long noteId = TEST_NOTE_ID;
        NoteCharacterApiDto noteCharacterApiDto = new NoteCharacterApiDto();
        noteCharacterApiDto.setTitle(TEST_NOTE_TITLE);
        noteCharacterApiDto.setContent(TEST_NOTE_CONTENT);

        when(noteCharacterService.updateNote(noteId, noteCharacterApiDto))
                .thenThrow(new CharacterNoteNotFoundException(ExceptionMessage.CHARACTER_NOTE_NOT_FOUND.getMessage()));

        mockMvc.perform(put("/character-notes/{noteId}", noteId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(noteCharacterApiDto)))
                            .andExpect(status().isNotFound())
                            .andExpect(jsonPath("$.message").value(ExceptionMessage.CHARACTER_NOTE_NOT_FOUND.getMessage()));
    }

    @Test
    void updateNote_UpdateError() throws Exception {
        Long noteId = TEST_NOTE_ID;
        NoteCharacterApiDto noteCharacterApiDto = new NoteCharacterApiDto();
        noteCharacterApiDto.setTitle(TEST_NOTE_TITLE);
        noteCharacterApiDto.setContent(TEST_NOTE_CONTENT);

        when(noteCharacterService.updateNote(noteId, noteCharacterApiDto))
                .thenThrow(new CharacterNoteSavingErrorException(ExceptionMessage.CHARACTER_NOTE_UPDATE_ERROR.getMessage()));

        mockMvc.perform(put("/character-notes/{noteId}", noteId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(noteCharacterApiDto)))
                            .andExpect(status().isInternalServerError())
                            .andExpect(jsonPath("$.message").value(ExceptionMessage.CHARACTER_NOTE_UPDATE_ERROR.getMessage()));
    }

    @Test
    void deleteNote_Success() throws Exception {
        Long noteId = TEST_NOTE_ID;

        doNothing().when(noteCharacterService).deleteNote(noteId);

        mockMvc.perform(delete("/character-notes/{noteId}", noteId))
                .andExpect(status().isOk());

        verify(noteCharacterService).deleteNote(noteId);
    }

    @Test
    void deleteNote_DeleteError() throws Exception {
        Long noteId = TEST_NOTE_ID;

        doThrow(new CharacterNoteSavingErrorException(ExceptionMessage.CHARACTER_NOTE_DELETE_ERROR.getMessage()))
                .when(noteCharacterService).deleteNote(noteId);

        mockMvc.perform(delete("/character-notes/{noteId}", noteId))
                .andExpect(status().isInternalServerError())
                .andExpect(jsonPath("$.message").value(ExceptionMessage.CHARACTER_NOTE_DELETE_ERROR.getMessage()));
    }

    private String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
