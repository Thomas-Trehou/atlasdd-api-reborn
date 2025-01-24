package fr.ttl.atlasdd.service.global.impl;

import fr.ttl.atlasdd.apidto.NoteCharacterApiDto;
import fr.ttl.atlasdd.mapper.NoteCharacterMapper;
import fr.ttl.atlasdd.repository.NoteCharacterRepo;
import fr.ttl.atlasdd.repository.custom.CustomCharacterSheetRepo;
import fr.ttl.atlasdd.repository.ogl5.CharacterSheetRepo;
import fr.ttl.atlasdd.service.global.NoteCharacterService;
import fr.ttl.atlasdd.sqldto.NoteCharacterSqlDto;
import fr.ttl.atlasdd.sqldto.custom.CustomCharacterSheetSqlDto;
import fr.ttl.atlasdd.sqldto.ogl5.CharacterSheetSqlDto;
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

        CharacterSheetSqlDto characterSheetSqlDto = ogl5CharacterSheetRepository.findById(characterSheetId).orElseThrow();

        NoteCharacterSqlDto noteCharacterSqlDto = noteCharacterMapper.toSqlDto(noteCharacterApiDto);
        noteCharacterSqlDto.setOgl5CharacterSheet(characterSheetSqlDto);

        NoteCharacterSqlDto savedNoteCharacterSqlDto = noteCharacterRepository.save(noteCharacterSqlDto);

        characterSheetSqlDto.getCharacterNotes().add(savedNoteCharacterSqlDto);

        ogl5CharacterSheetRepository.save(characterSheetSqlDto);

        return noteCharacterMapper.toApiDto(savedNoteCharacterSqlDto);
    }

    @Override
    public NoteCharacterApiDto createCustomCharacterNote(Long characterSheetId, NoteCharacterApiDto noteCharacterApiDto) {

        CustomCharacterSheetSqlDto characterSheetSqlDto = customCharacterSheetRepository.findById(characterSheetId).orElseThrow();

        NoteCharacterSqlDto noteCharacterSqlDto = noteCharacterMapper.toSqlDto(noteCharacterApiDto);
        noteCharacterSqlDto.setCustomCharacterSheet(characterSheetSqlDto);

        NoteCharacterSqlDto savedNoteCharacterSqlDto = noteCharacterRepository.save(noteCharacterSqlDto);

        characterSheetSqlDto.getCharacterNotes().add(savedNoteCharacterSqlDto);

        customCharacterSheetRepository.save(characterSheetSqlDto);

        return noteCharacterMapper.toApiDto(savedNoteCharacterSqlDto);
    }

    @Override
    public NoteCharacterApiDto updateNote(Long noteId, NoteCharacterApiDto noteCharacterApiDto) {

        NoteCharacterSqlDto noteCharacterSqlDto = noteCharacterRepository.findById(noteId).orElseThrow();
        noteCharacterMapper.updateSqlDto(noteCharacterApiDto, noteCharacterSqlDto);

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
