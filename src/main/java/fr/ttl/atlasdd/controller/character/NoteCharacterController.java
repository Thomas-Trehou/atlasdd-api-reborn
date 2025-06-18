package fr.ttl.atlasdd.controller.character;

import fr.ttl.atlasdd.apidto.character.NoteCharacterApiDto;
import fr.ttl.atlasdd.service.character.NoteCharacterService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/character-notes")
@RequiredArgsConstructor
public class NoteCharacterController {

    private final NoteCharacterService noteCharacterService;

    @Operation(summary = "Create a note for an Ogl5 character")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Note created, returns the created note"),
            @ApiResponse(responseCode = "404", description = "Ogl5 character not found", content = @Content),
            @ApiResponse(responseCode = "500", description =
                    "Error at note saving / " +
                    "Error at character saving", content = @Content)
    })
    @PostMapping("/ogl5/characters/{characterId}")
    public NoteCharacterApiDto addNoteToOgl5Character(
            @Parameter(description = "ID of the Ogl5 character", required = true)
            @PathVariable Long characterId,
            @Parameter(description = "Note to create", required = true)
            @RequestBody NoteCharacterApiDto noteCharacterApiDto
    ) {
        return noteCharacterService.createOgl5CharacterNote(characterId, noteCharacterApiDto);
    }

    @Operation(summary = "Get all notes by Ogl5 character ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Notes found, returns the notes"),
            @ApiResponse(responseCode = "500", description = "Something goes wrong at list creation", content = @Content)
    })
    @GetMapping("/ogl5/characters/{characterId}")
    public List<NoteCharacterApiDto> getNotesByOgl5CharacterId(
            @Parameter(description = "ID of the Ogl5 character", required = true)
            @PathVariable Long characterId
    ) {
        return noteCharacterService.getNotesByOgl5CharacterId(characterId);
    }

    @Operation(summary = "Create a note for a custom character")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Note created, returns the created note"),
            @ApiResponse(responseCode = "404", description = "Custom character not found", content = @Content),
            @ApiResponse(responseCode = "500", description =
                    "Error at note saving / " +
                    "Error at character saving", content = @Content)
    })
    @PostMapping("/custom/characters/{characterId}")
    public NoteCharacterApiDto addNoteToCustomCharacter(
            @Parameter(description = "ID of the custom character", required = true)
            @PathVariable Long characterId,
            @Parameter(description = "Note to create", required = true)
            @RequestBody NoteCharacterApiDto noteCharacterApiDto
    ) {
        return noteCharacterService.createCustomCharacterNote(characterId, noteCharacterApiDto);
    }

    @Operation(summary = "Get all notes by custom character ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Notes found, returns the notes"),
            @ApiResponse(responseCode = "500", description = "Something goes wrong at list creation", content = @Content)
    })
    @GetMapping("/custom/characters/{characterId}")
    public List<NoteCharacterApiDto> getNotesByCustomCharacterId(
            @Parameter(description = "ID of the custom character", required = true)
            @PathVariable Long characterId
    ) {
        return noteCharacterService.getNotesByCustomCharacterId(characterId);
    }

    @Operation(summary = "Update a note for a campaign")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Note updated, returns the updated note"),
            @ApiResponse(responseCode = "404", description = "Note not found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Error at note saving", content = @Content)
    })
    @PutMapping("/{noteId}")
    public NoteCharacterApiDto updateNoteCharacter(
            @Parameter(description = "ID of the note to update", required = true)
            @PathVariable Long noteId,
            @Parameter(description = "Note to update", required = true)
            @RequestBody NoteCharacterApiDto noteCharacterApiDto
    ) {
        return noteCharacterService.updateNote(noteId, noteCharacterApiDto);
    }

    @Operation(summary = "Delete a note of a character")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Note deleted"),
            @ApiResponse(responseCode = "404", description = "Note not found", content = @Content)
    })
    @DeleteMapping("/{noteId}")
    public void deleteNoteCharacter(
            @Parameter(description = "ID of the note to delete", required = true)
            @PathVariable Long noteId
    ) {
        noteCharacterService.deleteNote(noteId);
    }
}
