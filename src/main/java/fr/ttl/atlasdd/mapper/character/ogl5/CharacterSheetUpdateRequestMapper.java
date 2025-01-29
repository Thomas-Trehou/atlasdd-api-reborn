package fr.ttl.atlasdd.mapper.character.ogl5;

import fr.ttl.atlasdd.apidto.character.ogl5.CharacterSheetUpdateRequestApiDto;
import fr.ttl.atlasdd.sqldto.character.ogl5.CharacterSheetSqlDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CharacterSheetUpdateRequestMapper {

    CharacterSheetUpdateRequestMapper INSTANCE = Mappers.getMapper(CharacterSheetUpdateRequestMapper.class);

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
    void updateSqlDto(CharacterSheetUpdateRequestApiDto characterSheetUpdateRequest, @MappingTarget CharacterSheetSqlDto characterSheetSqlDto);
}
