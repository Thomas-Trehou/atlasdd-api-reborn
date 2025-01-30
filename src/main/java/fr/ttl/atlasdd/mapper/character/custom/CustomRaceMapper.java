package fr.ttl.atlasdd.mapper.character.custom;

import fr.ttl.atlasdd.apidto.character.custom.CustomRaceApiDto;
import fr.ttl.atlasdd.sqldto.character.custom.CustomRaceSqlDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CustomRaceMapper {

    CustomRaceMapper INSTANCE = Mappers.getMapper(CustomRaceMapper.class);

    CustomRaceApiDto toApiDto(CustomRaceSqlDto raceSqlDto);

    CustomRaceSqlDto toSqlDto(CustomRaceApiDto raceApiDto);

    void updateFromApiDto(CustomRaceApiDto raceApi, @MappingTarget CustomRaceSqlDto raceSql);
}
