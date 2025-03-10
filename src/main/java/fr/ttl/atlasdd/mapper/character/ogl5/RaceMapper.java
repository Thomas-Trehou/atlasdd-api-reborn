package fr.ttl.atlasdd.mapper.character.ogl5;

import fr.ttl.atlasdd.apidto.character.ogl5.RaceApiDto;
import fr.ttl.atlasdd.entity.character.ogl5.Ogl5Race;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface RaceMapper {

    RaceApiDto toApiDto(Ogl5Race ogl5Race);

    @Mapping(target = "characterSheets", ignore = true)
    Ogl5Race toEntity(RaceApiDto raceApiDto);

    @Mapping(target = "characterSheets", ignore = true)
    void updateFromApiDto(RaceApiDto raceApi, @MappingTarget Ogl5Race raceSql);
}
