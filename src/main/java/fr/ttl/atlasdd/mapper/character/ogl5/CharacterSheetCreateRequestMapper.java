package fr.ttl.atlasdd.mapper.character.ogl5;

import fr.ttl.atlasdd.apidto.character.ogl5.CharacterSheetCreateRequestApiDto;
import fr.ttl.atlasdd.entity.character.ogl5.Ogl5CharacterSheet;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CharacterSheetCreateRequestMapper {

    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "characterNotes", ignore = true)
    @Mapping(target = "campaigns", ignore = true)
    @Mapping(target = "owner", ignore = true)
    @Mapping(target = "race", ignore = true)
    @Mapping(target = "background", ignore = true)
    @Mapping(target = "classe", ignore = true)
    @Mapping(target = "skills", ignore = true)
    @Mapping(target = "preparedSpells", ignore = true)
    @Mapping(target = "weapons", ignore = true)
    @Mapping(target = "armor", ignore = true)
    @Mapping(target = "shield", ignore = true)
    @Mapping(target = "alignment", ignore = true)
    @Mapping(target = "status", ignore = true)
    void updateSqlDto(CharacterSheetCreateRequestApiDto characterSheetCreateRequestApiDto, @MappingTarget Ogl5CharacterSheet ogl5CharacterSheet);
}
