package fr.ttl.atlasdd.mapper.character.custom;

import fr.ttl.atlasdd.apidto.character.custom.CustomClassApiDto;
import fr.ttl.atlasdd.sqldto.character.custom.CustomClassSqlDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CustomClassMapper {

    CustomClassMapper INSTANCE = Mappers.getMapper(CustomClassMapper.class);

    CustomClassApiDto toApiDto(CustomClassSqlDto classSqlDto);

    CustomClassSqlDto toSqlDto(CustomClassApiDto classApiDto);

    void updateSqlDto(CustomClassApiDto classApiDto, @MappingTarget CustomClassSqlDto classSqlDto);
}
