package fr.ttl.atlasdd.mapper.character.ogl5;

import fr.ttl.atlasdd.apidto.character.ogl5.RaceApiDto;
import fr.ttl.atlasdd.sqldto.character.ogl5.RaceSqlDto;
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
