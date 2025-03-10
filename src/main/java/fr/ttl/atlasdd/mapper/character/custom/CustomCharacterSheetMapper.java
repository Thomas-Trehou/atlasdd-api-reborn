package fr.ttl.atlasdd.mapper.character.custom;

import fr.ttl.atlasdd.apidto.character.custom.CustomCharacterSheetApiDto;
import fr.ttl.atlasdd.entity.character.custom.CustomCharacterSheet;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CustomCharacterSheetMapper {

    CustomCharacterSheetApiDto toApiDto(CustomCharacterSheet characterSheetSqlDto);

    CustomCharacterSheet toEntity(CustomCharacterSheetApiDto characterSheetApiDto);

    void updateSqlDto(CustomCharacterSheetApiDto characterSheetApiDto, @MappingTarget CustomCharacterSheet characterSheetSqlDto);
}
