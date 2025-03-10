package fr.ttl.atlasdd.mapper.character.custom;

import fr.ttl.atlasdd.apidto.character.custom.CustomRaceApiDto;
import fr.ttl.atlasdd.entity.character.custom.CustomRace;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CustomRaceMapper {

    CustomRaceApiDto toApiDto(CustomRace raceSqlDto);

    @Mapping(target = "characterSheets", ignore = true)
    CustomRace toEntity(CustomRaceApiDto raceApiDto);

    @Mapping(target = "characterSheets", ignore = true)
    void updateFromApiDto(CustomRaceApiDto raceApi, @MappingTarget CustomRace raceSql);
}
