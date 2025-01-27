package fr.ttl.atlasdd.mapper.ogl5;

import fr.ttl.atlasdd.apidto.ogl5.RaceApiDto;
import fr.ttl.atlasdd.sqldto.ogl5.RaceSqlDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface RaceMapper {

    RaceMapper INSTANCE = Mappers.getMapper(RaceMapper.class);

    RaceApiDto toApiDto(RaceSqlDto raceSqlDto);

    RaceSqlDto toSqlDto(RaceApiDto raceApiDto);

    void updateFromApiDto(RaceApiDto raceApi, @MappingTarget RaceSqlDto raceSql);
}
