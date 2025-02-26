package fr.ttl.atlasdd.mapper.character.ogl5;

import fr.ttl.atlasdd.apidto.character.ogl5.BackgroundApiDto;
import fr.ttl.atlasdd.entity.character.ogl5.Ogl5Background;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface BackgroundMapper {

    BackgroundMapper INSTANCE = Mappers.getMapper(BackgroundMapper.class);

    BackgroundApiDto toApiDto(Ogl5Background ogl5Background);

    Ogl5Background toSqlDto(BackgroundApiDto backgroundApiDto);

    void updateFromApiDto(BackgroundApiDto backgroundApiDto, @MappingTarget Ogl5Background ogl5Background);
}
