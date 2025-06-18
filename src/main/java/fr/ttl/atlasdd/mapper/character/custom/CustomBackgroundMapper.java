package fr.ttl.atlasdd.mapper.character.custom;

import fr.ttl.atlasdd.apidto.character.BackgroundApiDto;
import fr.ttl.atlasdd.entity.character.custom.CustomBackground;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CustomBackgroundMapper {

    BackgroundApiDto toApiDto(CustomBackground backgroundSqlDto);

    @Mapping(target = "characterSheets", ignore = true)
    CustomBackground toEntity(BackgroundApiDto backgroundApiDto);

    @Mapping(target = "characterSheets", ignore = true)
    void updateFromApiDto(BackgroundApiDto backgroundApiDto, @MappingTarget CustomBackground backgroundSqlDto);
}
