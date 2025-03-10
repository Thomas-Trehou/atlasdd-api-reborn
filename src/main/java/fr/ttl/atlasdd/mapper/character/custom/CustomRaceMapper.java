package fr.ttl.atlasdd.mapper.character.custom;

import fr.ttl.atlasdd.apidto.character.custom.CustomRaceApiDto;
import fr.ttl.atlasdd.entity.character.custom.CustomRace;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CustomRaceMapper {

    CustomRaceApiDto toApiDto(CustomRace raceSqlDto);

    CustomRace toEntity(CustomRaceApiDto raceApiDto);

    void updateFromApiDto(CustomRaceApiDto raceApi, @MappingTarget CustomRace raceSql);
}
