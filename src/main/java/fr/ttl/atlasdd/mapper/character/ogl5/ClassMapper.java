package fr.ttl.atlasdd.mapper.character.ogl5;

import fr.ttl.atlasdd.apidto.character.ogl5.ClassApiDto;
import fr.ttl.atlasdd.sqldto.character.ogl5.ClassSqlDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ClassMapper {

    ClassMapper INSTANCE = Mappers.getMapper(ClassMapper.class);

    ClassApiDto toApiDto(ClassSqlDto classSqlDto);

    ClassSqlDto toSqlDto(ClassApiDto classApiDto);

    void updateSqlDto(ClassApiDto classApiDto, @MappingTarget ClassSqlDto classSqlDto);
}
