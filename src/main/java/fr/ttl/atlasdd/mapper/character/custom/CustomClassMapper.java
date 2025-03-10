package fr.ttl.atlasdd.mapper.character.custom;

import fr.ttl.atlasdd.apidto.character.custom.CustomClassApiDto;
import fr.ttl.atlasdd.entity.character.custom.CustomClass;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CustomClassMapper {

    CustomClassApiDto toApiDto(CustomClass classSqlDto);

    CustomClass toSqlDto(CustomClassApiDto classApiDto);

    void updateSqlDto(CustomClassApiDto classApiDto, @MappingTarget CustomClass classSqlDto);
}
