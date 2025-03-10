package fr.ttl.atlasdd.mapper.character.custom;

import fr.ttl.atlasdd.apidto.character.custom.CustomBackgroundApiDto;
import fr.ttl.atlasdd.entity.character.custom.CustomBackground;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CustomBackgroundMapper {

    CustomBackgroundApiDto toApiDto(CustomBackground backgroundSqlDto);

    @Mapping(target = "characterSheets", ignore = true)
    CustomBackground toEntity(CustomBackgroundApiDto backgroundApiDto);

    @Mapping(target = "characterSheets", ignore = true)
    void updateFromApiDto(CustomBackgroundApiDto backgroundApiDto, @MappingTarget CustomBackground backgroundSqlDto);
}
