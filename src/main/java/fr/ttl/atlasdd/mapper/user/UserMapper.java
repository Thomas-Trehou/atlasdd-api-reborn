package fr.ttl.atlasdd.mapper.user;

import fr.ttl.atlasdd.apidto.user.UserApiDto;
import fr.ttl.atlasdd.entity.user.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserApiDto toApiDto(User user);

    User toSqlDto(UserApiDto userApiDto);

    void updateSqlDto(UserApiDto userApiDto, @MappingTarget User user);
}
