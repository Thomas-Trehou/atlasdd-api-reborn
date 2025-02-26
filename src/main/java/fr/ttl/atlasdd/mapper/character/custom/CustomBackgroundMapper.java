package fr.ttl.atlasdd.mapper.character.custom;

import fr.ttl.atlasdd.apidto.character.custom.CustomBackgroundApiDto;
import fr.ttl.atlasdd.entity.character.custom.CustomBackground;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CustomBackgroundMapper {

    CustomBackgroundMapper INSTANCE = Mappers.getMapper(CustomBackgroundMapper.class);

    CustomBackgroundApiDto toApiDto(CustomBackground backgroundSqlDto);

    CustomBackground toSqlDto(CustomBackgroundApiDto backgroundApiDto);

    void updateFromApiDto(CustomBackgroundApiDto backgroundApiDto, @MappingTarget CustomBackground backgroundSqlDto);
}
