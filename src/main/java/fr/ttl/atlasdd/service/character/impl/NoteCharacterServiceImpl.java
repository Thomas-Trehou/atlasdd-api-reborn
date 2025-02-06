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
import fr.ttl.atlasdd.sqldto.character.NoteCharacterSqlDto;
import fr.ttl.atlasdd.sqldto.character.custom.CustomCharacterSheetSqlDto;
import fr.ttl.atlasdd.sqldto.character.ogl5.CharacterSheetSqlDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class NoteCharacterServiceImpl implements NoteCharacterService {

    private final NoteCharacterRepo noteCharacterRepository;
    private final CharacterSheetRepo ogl5CharacterSheetRepository;
    private final CustomCharacterSheetRepo customCharacterSheetRepository;
    private final NoteCharacterMapper noteCharacterMapper;

    public NoteCharacterServiceImpl(
            NoteCharacterRepo noteCharacterRepository,
            CharacterSheetRepo ogl5CharacterSheetRepository,
            CustomCharacterSheetRepo customCharacterSheetRepository,
            NoteCharacterMapper noteCharacterMapper
    ) {
        this.noteCharacterRepository = noteCharacterRepository;
        this.ogl5CharacterSheetRepository = ogl5CharacterSheetRepository;
        this.customCharacterSheetRepository = customCharacterSheetRepository;
        this.noteCharacterMapper = noteCharacterMapper;
    }

    @Override
    public NoteCharacterApiDto createOgl5CharacterNote(Long characterSheetId, NoteCharacterApiDto noteCharacterApiDto) {

        CharacterSheetSqlDto characterSheetSqlDto = ogl5CharacterSheetRepository.findById(characterSheetId)
                .orElseThrow(() -> new Ogl5CharacterNotFoundException("Personnage non trouvé"));

        NoteCharacterSqlDto noteCharacterSqlDto = noteCharacterMapper.toSqlDto(noteCharacterApiDto);
        noteCharacterSqlDto.setOgl5CharacterSheet(characterSheetSqlDto);

        NoteCharacterSqlDto savedNoteCharacterSqlDto;

        try {
            savedNoteCharacterSqlDto = noteCharacterRepository.save(noteCharacterSqlDto);
        } catch (Exception e) {
            throw new CharacterNoteSavingErrorException("Erreur lors de la sauvegarde de la note");
        }

        characterSheetSqlDto.getCharacterNotes().add(savedNoteCharacterSqlDto);

        try {
            ogl5CharacterSheetRepository.save(characterSheetSqlDto);
        } catch (Exception e) {
            throw new Ogl5CharacterSavingErrorException("Erreur lors de la sauvegarde du personnage");
        }

        return noteCharacterMapper.toApiDto(savedNoteCharacterSqlDto);
    }

    @Override
    public NoteCharacterApiDto createCustomCharacterNote(Long characterSheetId, NoteCharacterApiDto noteCharacterApiDto) {

        CustomCharacterSheetSqlDto characterSheetSqlDto = customCharacterSheetRepository.findById(characterSheetId)
                .orElseThrow(() -> new CustomCharacterNotFoundException("Personnage non trouvé"));

        NoteCharacterSqlDto noteCharacterSqlDto = noteCharacterMapper.toSqlDto(noteCharacterApiDto);
        noteCharacterSqlDto.setCustomCharacterSheet(characterSheetSqlDto);

        NoteCharacterSqlDto savedNoteCharacterSqlDto;

        try {
            savedNoteCharacterSqlDto = noteCharacterRepository.save(noteCharacterSqlDto);
        } catch (Exception e) {
            throw new CharacterNoteSavingErrorException("Erreur lors de la sauvegarde de la note");
        }

        characterSheetSqlDto.getCharacterNotes().add(savedNoteCharacterSqlDto);

        try {
            customCharacterSheetRepository.save(characterSheetSqlDto);
        } catch (Exception e) {
            throw new CustomCharacterSavingErrorException("Erreur lors de la sauvegarde du personnage");
        }

        return noteCharacterMapper.toApiDto(savedNoteCharacterSqlDto);
    }

    @Override
    public NoteCharacterApiDto updateNote(Long noteId, NoteCharacterApiDto noteCharacterApiDto) {

        NoteCharacterSqlDto noteCharacterSqlDto = noteCharacterRepository.findById(noteId)
                .orElseThrow(() -> new CharacterNoteNotFoundException("Note non trouvée"));

        noteCharacterSqlDto.setContent(noteCharacterApiDto.getContent());
        noteCharacterSqlDto.setTitle(noteCharacterApiDto.getTitle());

        try {
            return noteCharacterMapper.toApiDto(noteCharacterRepository.save(noteCharacterSqlDto));
        } catch (Exception e) {
            throw new CharacterNoteSavingErrorException("Erreur lors de la sauvegarde de la note");
        }
    }

    @Override
    public List<NoteCharacterApiDto> getNotesByOgl5CharacterId(Long characterId) {
        return noteCharacterRepository.findAllByOgl5CharacterSheet_Id(characterId).stream()
                .map(noteCharacterMapper::toApiDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<NoteCharacterApiDto> getNotesByCustomCharacterId(Long characterId) {
        return noteCharacterRepository.findAllByCustomCharacterSheet_Id(characterId).stream()
                .map(noteCharacterMapper::toApiDto)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteNote(Long noteId) {
        try {
            noteCharacterRepository.deleteById(noteId);
        } catch (Exception e) {
            throw new CharacterNoteSavingErrorException("Erreur lors de la suppression de la note");
        }
    }


}
