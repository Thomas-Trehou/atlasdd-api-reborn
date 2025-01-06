package fr.ttl.atlasdd.mapper;

import fr.ttl.atlasdd.apidto.RaceApiDto;
import fr.ttl.atlasdd.sqldto.RaceSqlDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RaceMapper {

    RaceMapper INSTANCE = Mappers.getMapper(RaceMapper.class);

    RaceApiDto toApiDto(RaceSqlDto raceSqlDto);

    RaceSqlDto toSqlDto(RaceApiDto raceApiDto);

    void updateFromApiDto(RaceApiDto raceApi, @MappingTarget RaceSqlDto raceSql);
}
