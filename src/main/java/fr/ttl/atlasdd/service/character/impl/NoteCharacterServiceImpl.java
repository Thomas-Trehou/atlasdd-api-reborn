package fr.ttl.atlasdd.service.character.impl;

import fr.ttl.atlasdd.apidto.character.NoteCharacterApiDto;
import fr.ttl.atlasdd.exception.character.CharacterNoteNotFoundException;
import fr.ttl.atlasdd.exception.character.CharacterNoteSavingErrorException;
import fr.ttl.atlasdd.exception.character.custom.notfound.CustomCharacterNotFoundException;
import fr.ttl.atlasdd.exception.character.custom.savingerror.CustomCharacterSavingErrorException;
import fr.ttl.atlasdd.exception.character.ogl5.notfound.Ogl5CharacterNotFoundException;
import fr.ttl.atlasdd.exception.character.ogl5.savingerror.Ogl5CharacterSavingErrorException;
import fr.ttl.atlasdd.mapper.character.NoteCharacterMapper;
import fr.ttl.atlasdd.repository.character.NoteCharacterRepo;
import fr.ttl.atlasdd.repository.character.custom.CustomCharacterSheetRepo;
import fr.ttl.atlasdd.repository.character.ogl5.CharacterSheetRepo;
import fr.ttl.atlasdd.service.character.NoteCharacterService;
import fr.ttl.atlasdd.entity.character.CharacterNote;
import fr.ttl.atlasdd.entity.character.custom.CustomCharacterSheet;
import fr.ttl.atlasdd.entity.character.ogl5.Ogl5CharacterSheet;
import fr.ttl.atlasdd.utils.exception.ExceptionMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NoteCharacterServiceImpl implements NoteCharacterService {

    private final NoteCharacterRepo noteCharacterRepository;
    private final CharacterSheetRepo ogl5CharacterSheetRepository;
    private final CustomCharacterSheetRepo customCharacterSheetRepository;
    private final NoteCharacterMapper noteCharacterMapper;

    @Override
    public NoteCharacterApiDto createOgl5CharacterNote(Long characterSheetId, NoteCharacterApiDto noteCharacterApiDto) {

        Ogl5CharacterSheet ogl5CharacterSheet = ogl5CharacterSheetRepository.findById(characterSheetId)
                .orElseThrow(() -> new Ogl5CharacterNotFoundException(ExceptionMessage.CHARACTER_NOT_FOUND.getMessage()));

        CharacterNote characterNote = noteCharacterMapper.toSqlDto(noteCharacterApiDto);
        characterNote.setOgl5CharacterSheet(ogl5CharacterSheet);

        CharacterNote savedCharacterNote;

        try {
            savedCharacterNote = noteCharacterRepository.save(characterNote);
        } catch (Exception e) {
            throw new CharacterNoteSavingErrorException(ExceptionMessage.CHARACTER_NOTE_SAVE_ERROR.getMessage());
        }

        ogl5CharacterSheet.getCharacterNotes().add(savedCharacterNote);

        try {
            ogl5CharacterSheetRepository.save(ogl5CharacterSheet);
        } catch (Exception e) {
            throw new Ogl5CharacterSavingErrorException(ExceptionMessage.CHARACTER_UPDATE_ERROR.getMessage());
        }

        return noteCharacterMapper.toApiDto(savedCharacterNote);
    }

    @Override
    public NoteCharacterApiDto createCustomCharacterNote(Long characterSheetId, NoteCharacterApiDto noteCharacterApiDto) {

        CustomCharacterSheet characterSheetSqlDto = customCharacterSheetRepository.findById(characterSheetId)
                .orElseThrow(() -> new CustomCharacterNotFoundException(ExceptionMessage.CHARACTER_NOT_FOUND.getMessage()));

        CharacterNote characterNote = noteCharacterMapper.toSqlDto(noteCharacterApiDto);
        characterNote.setCustomCharacterSheet(characterSheetSqlDto);

        CharacterNote savedCharacterNote;

        try {
            savedCharacterNote = noteCharacterRepository.save(characterNote);
        } catch (Exception e) {
            throw new CharacterNoteSavingErrorException(ExceptionMessage.CHARACTER_NOTE_SAVE_ERROR.getMessage());
        }

        characterSheetSqlDto.getCharacterNotes().add(savedCharacterNote);

        try {
            customCharacterSheetRepository.save(characterSheetSqlDto);
        } catch (Exception e) {
            throw new CustomCharacterSavingErrorException(ExceptionMessage.CHARACTER_UPDATE_ERROR.getMessage());
        }

        return noteCharacterMapper.toApiDto(savedCharacterNote);
    }

    @Override
    public NoteCharacterApiDto updateNote(Long noteId, NoteCharacterApiDto noteCharacterApiDto) {

        CharacterNote characterNote = noteCharacterRepository.findById(noteId)
                .orElseThrow(() -> new CharacterNoteNotFoundException(ExceptionMessage.CHARACTER_NOTE_NOT_FOUND.getMessage()));

        characterNote.setContent(noteCharacterApiDto.getContent());
        characterNote.setTitle(noteCharacterApiDto.getTitle());

        try {
            return noteCharacterMapper.toApiDto(noteCharacterRepository.save(characterNote));
        } catch (Exception e) {
            throw new CharacterNoteSavingErrorException(ExceptionMessage.CHARACTER_NOTE_UPDATE_ERROR.getMessage());
        }
    }

    @Override
    public List<NoteCharacterApiDto> getNotesByOgl5CharacterId(Long characterId) {
        if (!ogl5CharacterSheetRepository.existsById(characterId)) {
            throw new Ogl5CharacterNotFoundException(ExceptionMessage.CHARACTER_NOT_FOUND.getMessage());
        }

        return noteCharacterRepository.findAllByOgl5CharacterSheet_Id(characterId).stream()
                .map(noteCharacterMapper::toApiDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<NoteCharacterApiDto> getNotesByCustomCharacterId(Long characterId) {
        if (!customCharacterSheetRepository.existsById(characterId)) {
            throw new CustomCharacterNotFoundException(ExceptionMessage.CHARACTER_NOT_FOUND.getMessage());
        }

        return noteCharacterRepository.findAllByCustomCharacterSheet_Id(characterId).stream()
                .map(noteCharacterMapper::toApiDto)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteNote(Long noteId) {
        try {
            noteCharacterRepository.deleteById(noteId);
        } catch (Exception e) {
            throw new CharacterNoteSavingErrorException(ExceptionMessage.CHARACTER_NOTE_DELETE_ERROR.getMessage());
        }
    }


}
