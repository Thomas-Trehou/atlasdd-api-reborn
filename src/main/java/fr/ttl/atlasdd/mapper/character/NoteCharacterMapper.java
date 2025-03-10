package fr.ttl.atlasdd.mapper.character;

import fr.ttl.atlasdd.apidto.character.NoteCharacterApiDto;
import fr.ttl.atlasdd.entity.character.CharacterNote;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface NoteCharacterMapper {

    NoteCharacterApiDto toApiDto(CharacterNote characterNote);

    @Mapping(target = "ogl5CharacterSheet", ignore = true)
    @Mapping(target = "customCharacterSheet", ignore = true)
    CharacterNote toEntity(NoteCharacterApiDto noteCharacterApiDto);

    @Mapping(target = "ogl5CharacterSheet", ignore = true)
    @Mapping(target = "customCharacterSheet", ignore = true)
    void updateSqlDto(NoteCharacterApiDto noteCharacterApiDto, @MappingTarget CharacterNote characterNote);
}
