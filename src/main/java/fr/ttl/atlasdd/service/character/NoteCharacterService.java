package fr.ttl.atlasdd.service.character;

import fr.ttl.atlasdd.apidto.character.NoteCharacterApiDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface NoteCharacterService {

        NoteCharacterApiDto createOgl5CharacterNote(Long characterSheetId, NoteCharacterApiDto characterApiDto);

        NoteCharacterApiDto createCustomCharacterNote(Long characterSheetId, NoteCharacterApiDto characterApiDto);

        NoteCharacterApiDto updateNote(Long noteId, NoteCharacterApiDto characterApiDto);

        List<NoteCharacterApiDto> getNotesByOgl5CharacterId(Long characterId);

        List<NoteCharacterApiDto> getNotesByCustomCharacterId(Long characterId);

        void deleteNote(Long id);
}
