package fr.ttl.atlasdd.mapper.ogl5;

import fr.ttl.atlasdd.apidto.ogl5.BackgroundApiDto;
import fr.ttl.atlasdd.sqldto.ogl5.BackgroundSqlDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface BackgroundMapper {

    BackgroundMapper INSTANCE = Mappers.getMapper(BackgroundMapper.class);

    BackgroundApiDto toApiDto(BackgroundSqlDto backgroundSqlDto);

    BackgroundSqlDto toSqlDto(BackgroundApiDto backgroundApiDto);

    void updateFromApiDto(BackgroundApiDto backgroundApiDto, @MappingTarget BackgroundSqlDto backgroundSqlDto);
}
