package fr.ttl.atlasdd.mapper.character.ogl5;

import fr.ttl.atlasdd.apidto.character.BackgroundApiDto;
import fr.ttl.atlasdd.entity.character.ogl5.Ogl5Background;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface BackgroundMapper {

    BackgroundApiDto toApiDto(Ogl5Background ogl5Background);

    @Mapping(target = "characterSheets", ignore = true)
    Ogl5Background toEntity(BackgroundApiDto backgroundApiDto);

    @Mapping(target = "characterSheets", ignore = true)
    void updateFromApiDto(BackgroundApiDto backgroundApiDto, @MappingTarget Ogl5Background ogl5Background);
}
