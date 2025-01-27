package fr.ttl.atlasdd.mapper.custom;

import fr.ttl.atlasdd.apidto.custom.CustomCharacterSheetApiDto;
import fr.ttl.atlasdd.apidto.custom.CustomCharacterSheetCreateRequestApiDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CustomCharacterSheetCreateRequestMapper {

    CustomCharacterSheetCreateRequestMapper INSTANCE = Mappers.getMapper(CustomCharacterSheetCreateRequestMapper.class);

    @Mapping(target = "skills", ignore = true)
    @Mapping(target = "preparedSpells", ignore = true)
    CustomCharacterSheetApiDto toApiDto(CustomCharacterSheetCreateRequestApiDto characterSheetCreateRequest);
}
