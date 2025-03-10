package fr.ttl.atlasdd.mapper.user;

import fr.ttl.atlasdd.apidto.user.UserApiDto;
import fr.ttl.atlasdd.entity.user.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserApiDto toApiDto(User user);

    User toEntity(UserApiDto userApiDto);

    void updateSqlDto(UserApiDto userApiDto, @MappingTarget User user);
}
