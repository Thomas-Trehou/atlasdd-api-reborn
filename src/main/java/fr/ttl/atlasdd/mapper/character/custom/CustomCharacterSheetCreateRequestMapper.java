package fr.ttl.atlasdd.mapper.character.custom;

import fr.ttl.atlasdd.apidto.character.custom.CustomCharacterSheetApiDto;
import fr.ttl.atlasdd.apidto.character.custom.CustomCharacterSheetCreateRequestApiDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CustomCharacterSheetCreateRequestMapper {

    @Mapping(target = "skills", ignore = true)
    @Mapping(target = "preparedSpells", ignore = true)
    CustomCharacterSheetApiDto toApiDto(CustomCharacterSheetCreateRequestApiDto characterSheetCreateRequest);
}
