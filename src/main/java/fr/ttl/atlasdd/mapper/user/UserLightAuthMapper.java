package fr.ttl.atlasdd.mapper.user;

import fr.ttl.atlasdd.apidto.user.UserLightAuthApiDto;
import fr.ttl.atlasdd.entity.user.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserLightAuthMapper {

    UserLightAuthMapper INSTANCE = Mappers.getMapper(UserLightAuthMapper.class);

    UserLightAuthApiDto toApiDto(User user);

    User toSqlDto(UserLightAuthApiDto userLightAuthApiDto);
}
