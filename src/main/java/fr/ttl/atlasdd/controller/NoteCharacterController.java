package fr.ttl.atlasdd.controller;

import fr.ttl.atlasdd.apidto.NoteCharacterApiDto;
import fr.ttl.atlasdd.service.global.NoteCharacterService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/note-character")
public class NoteCharacterController {

    private final NoteCharacterService noteCharacterService;

    public NoteCharacterController(NoteCharacterService noteCharacterService) {
        this.noteCharacterService = noteCharacterService;
    }

    @PostMapping("/ogl5/characters/{characterId}")
    public NoteCharacterApiDto addNoteToOgl5Character(@PathVariable Long characterId, @RequestBody NoteCharacterApiDto noteCharacterApiDto) {
        return noteCharacterService.createOgl5CharacterNote(characterId, noteCharacterApiDto);
    }

    @GetMapping("/ogl5/characters/{characterId}")
    public List<NoteCharacterApiDto> getNotesByOgl5CharacterId(@PathVariable Long characterId) {
        return noteCharacterService.getNotesByOgl5CharacterId(characterId);
    }

    @PostMapping("/custom/characters/{characterId}")
    public NoteCharacterApiDto addNoteToCustomCharacter(@PathVariable Long characterId, @RequestBody NoteCharacterApiDto noteCharacterApiDto) {
        return noteCharacterService.createCustomCharacterNote(characterId, noteCharacterApiDto);
    }

    @GetMapping("/custom/characters/{characterId}")
    public List<NoteCharacterApiDto> getNotesByCustomCharacterId(@PathVariable Long characterId) {
        return noteCharacterService.getNotesByCustomCharacterId(characterId);
    }

    @PutMapping("/{noteId}")
    public NoteCharacterApiDto updateNoteCharacter(@PathVariable Long noteId, @RequestBody NoteCharacterApiDto noteCharacterApiDto) {
        return noteCharacterService.updateNote(noteId, noteCharacterApiDto);
    }

    @DeleteMapping("/{noteId}")
    public void deleteNoteCharacter(@PathVariable Long noteId) {
        noteCharacterService.deleteNote(noteId);
    }
}
