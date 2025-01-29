package fr.ttl.atlasdd.mapper.character.custom;

import fr.ttl.atlasdd.apidto.character.custom.CustomCharacterSheetApiDto;
import fr.ttl.atlasdd.apidto.character.custom.CustomCharacterSheetUpdateRequestApiDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CustomCharacterSheetUpdateRequestMapper {

    CustomCharacterSheetUpdateRequestMapper INSTANCE = Mappers.getMapper(CustomCharacterSheetUpdateRequestMapper.class);

    @Mapping(target = "skills", ignore = true)
    @Mapping(target = "preparedSpells", ignore = true)
    CustomCharacterSheetApiDto toApiDto(CustomCharacterSheetUpdateRequestApiDto characterSheetUpdateRequestApiDto);
}
