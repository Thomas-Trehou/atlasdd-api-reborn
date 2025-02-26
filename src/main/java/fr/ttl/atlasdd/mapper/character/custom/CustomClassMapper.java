package fr.ttl.atlasdd.mapper.character.custom;

import fr.ttl.atlasdd.apidto.character.custom.CustomClassApiDto;
import fr.ttl.atlasdd.entity.character.custom.CustomClass;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CustomClassMapper {

    CustomClassMapper INSTANCE = Mappers.getMapper(CustomClassMapper.class);

    CustomClassApiDto toApiDto(CustomClass classSqlDto);

    CustomClass toSqlDto(CustomClassApiDto classApiDto);

    void updateSqlDto(CustomClassApiDto classApiDto, @MappingTarget CustomClass classSqlDto);
}
