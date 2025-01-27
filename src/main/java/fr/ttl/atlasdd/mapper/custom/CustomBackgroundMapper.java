package fr.ttl.atlasdd.mapper.custom;

import fr.ttl.atlasdd.apidto.custom.CustomBackgroundApiDto;
import fr.ttl.atlasdd.sqldto.custom.CustomBackgroundSqlDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CustomBackgroundMapper {

    CustomBackgroundMapper INSTANCE = Mappers.getMapper(CustomBackgroundMapper.class);

    CustomBackgroundApiDto toApiDto(CustomBackgroundSqlDto backgroundSqlDto);

    CustomBackgroundSqlDto toSqlDto(CustomBackgroundApiDto backgroundApiDto);

    void updateFromApiDto(CustomBackgroundApiDto backgroundApiDto, @MappingTarget CustomBackgroundSqlDto backgroundSqlDto);
}
