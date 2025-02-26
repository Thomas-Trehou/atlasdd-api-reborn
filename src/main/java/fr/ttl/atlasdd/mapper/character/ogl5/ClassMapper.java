package fr.ttl.atlasdd.mapper.character.ogl5;

import fr.ttl.atlasdd.apidto.character.ogl5.ClassApiDto;
import fr.ttl.atlasdd.entity.character.ogl5.Ogl5Class;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ClassMapper {

    ClassMapper INSTANCE = Mappers.getMapper(ClassMapper.class);

    ClassApiDto toApiDto(Ogl5Class ogl5Class);

    Ogl5Class toSqlDto(ClassApiDto classApiDto);

    void updateSqlDto(ClassApiDto classApiDto, @MappingTarget Ogl5Class ogl5Class);
}
