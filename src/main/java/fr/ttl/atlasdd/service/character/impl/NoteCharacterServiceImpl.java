package fr.ttl.atlasdd.service.character.impl;

import fr.ttl.atlasdd.apidto.character.NoteCharacterApiDto;
import fr.ttl.atlasdd.exception.character.custom.notfound.CustomCharacterNotFoundException;
import fr.ttl.atlasdd.exception.character.custom.savingerror.CustomCharacterSavingErrorException;
import fr.ttl.atlasdd.exception.character.ogl5.Ogl5CharacterNotFoundException;
import fr.ttl.atlasdd.exception.character.ogl5.Ogl5CharacterSavingErrorException;
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
                .orElseThrow(() -> new Ogl5CharacterNotFoundException("Personnage non trouvé", 404));

        NoteCharacterSqlDto noteCharacterSqlDto = noteCharacterMapper.toSqlDto(noteCharacterApiDto);
        noteCharacterSqlDto.setOgl5CharacterSheet(characterSheetSqlDto);

        NoteCharacterSqlDto savedNoteCharacterSqlDto = noteCharacterRepository.save(noteCharacterSqlDto);

        characterSheetSqlDto.getCharacterNotes().add(savedNoteCharacterSqlDto);

        try {
            ogl5CharacterSheetRepository.save(characterSheetSqlDto);
        } catch (Exception e) {
            throw new Ogl5CharacterSavingErrorException("Erreur lors de la sauvegarde du personnage", 500);
        }

        return noteCharacterMapper.toApiDto(savedNoteCharacterSqlDto);
    }

    @Override
    public NoteCharacterApiDto createCustomCharacterNote(Long characterSheetId, NoteCharacterApiDto noteCharacterApiDto) {

        CustomCharacterSheetSqlDto characterSheetSqlDto = customCharacterSheetRepository.findById(characterSheetId)
                .orElseThrow(() -> new CustomCharacterNotFoundException("Personnage non trouvé", 404));

        NoteCharacterSqlDto noteCharacterSqlDto = noteCharacterMapper.toSqlDto(noteCharacterApiDto);
        noteCharacterSqlDto.setCustomCharacterSheet(characterSheetSqlDto);

        NoteCharacterSqlDto savedNoteCharacterSqlDto = noteCharacterRepository.save(noteCharacterSqlDto);

        characterSheetSqlDto.getCharacterNotes().add(savedNoteCharacterSqlDto);

        try {
            customCharacterSheetRepository.save(characterSheetSqlDto);
        } catch (Exception e) {
            throw new CustomCharacterSavingErrorException("Erreur lors de la sauvegarde du personnage", 500);
        }

        return noteCharacterMapper.toApiDto(savedNoteCharacterSqlDto);
    }

    @Override
    public NoteCharacterApiDto updateNote(Long noteId, NoteCharacterApiDto noteCharacterApiDto) {

        NoteCharacterSqlDto noteCharacterSqlDto = noteCharacterRepository.findById(noteId).orElseThrow();
        noteCharacterSqlDto.setContent(noteCharacterApiDto.getContent());
        noteCharacterSqlDto.setTitle(noteCharacterApiDto.getTitle());

        NoteCharacterSqlDto savedNoteCharacterSqlDto = noteCharacterRepository.save(noteCharacterSqlDto);

        return noteCharacterMapper.toApiDto(savedNoteCharacterSqlDto);
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
        noteCharacterRepository.deleteById(noteId);
    }


}
