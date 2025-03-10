package fr.ttl.atlasdd.mapper.character.ogl5;

import fr.ttl.atlasdd.apidto.character.ogl5.CharacterSheetApiDto;
import fr.ttl.atlasdd.entity.character.ogl5.Ogl5CharacterSheet;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CharacterSheetMapper {

    CharacterSheetApiDto toApiDto(Ogl5CharacterSheet ogl5CharacterSheet);

    Ogl5CharacterSheet toSqlDto(CharacterSheetApiDto characterSheetApiDto);

    void updateSqlDto(CharacterSheetApiDto characterSheetApiDto, @MappingTarget Ogl5CharacterSheet ogl5CharacterSheet);
}
