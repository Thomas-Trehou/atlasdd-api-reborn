package fr.ttl.atlasdd.mapper.user;

import fr.ttl.atlasdd.apidto.user.CustomUserDetails;
import fr.ttl.atlasdd.entity.user.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserDetailsMapper {

    CustomUserDetails toUserDetails(User user);
}
