package fr.ttl.atlasdd.mapper;

import fr.ttl.atlasdd.apidto.UserApiDto;
import fr.ttl.atlasdd.sqldto.UserSqlDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserApiDto toApiDto(UserSqlDto userSqlDto);

    UserSqlDto toSqlDto(UserApiDto userApiDto);

    void updateSqlDto(UserApiDto userApiDto, @MappingTarget UserSqlDto userSqlDto);
}
