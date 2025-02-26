package fr.ttl.atlasdd.mapper.character;

import fr.ttl.atlasdd.apidto.character.NoteCharacterApiDto;
import fr.ttl.atlasdd.entity.character.CharacterNote;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface NoteCharacterMapper {

    NoteCharacterMapper INSTANCE = Mappers.getMapper(NoteCharacterMapper.class);

    NoteCharacterApiDto toApiDto(CharacterNote characterNote);

    CharacterNote toSqlDto(NoteCharacterApiDto noteCharacterApiDto);

    void updateSqlDto(NoteCharacterApiDto noteCharacterApiDto, @MappingTarget CharacterNote characterNote);
}
