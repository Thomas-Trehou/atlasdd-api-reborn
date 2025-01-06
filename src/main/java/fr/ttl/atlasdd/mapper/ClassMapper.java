package fr.ttl.atlasdd.mapper;

import fr.ttl.atlasdd.apidto.ClassApiDto;
import fr.ttl.atlasdd.sqldto.ClassSqlDto;
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
