package fr.ttl.atlasdd.mapper.character.custom;

import fr.ttl.atlasdd.apidto.character.custom.CustomCharacterSheetApiDto;
import fr.ttl.atlasdd.entity.character.custom.CustomCharacterSheet;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CustomCharacterSheetMapper {

    CustomCharacterSheetMapper INSTANCE = Mappers.getMapper(CustomCharacterSheetMapper.class);

    CustomCharacterSheetApiDto toApiDto(CustomCharacterSheet characterSheetSqlDto);

    CustomCharacterSheet toSqlDto(CustomCharacterSheetApiDto characterSheetApiDto);

    void updateSqlDto(CustomCharacterSheetApiDto characterSheetApiDto, @MappingTarget CustomCharacterSheet characterSheetSqlDto);
}
