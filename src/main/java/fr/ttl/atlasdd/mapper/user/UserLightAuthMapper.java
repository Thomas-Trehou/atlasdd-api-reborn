package fr.ttl.atlasdd.mapper.user;

import fr.ttl.atlasdd.apidto.user.UserLightAuthApiDto;
import fr.ttl.atlasdd.entity.user.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserLightAuthMapper {

    UserLightAuthApiDto toApiDto(User user);

    User toEntity(UserLightAuthApiDto userLightAuthApiDto);
}
