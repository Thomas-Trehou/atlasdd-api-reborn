package fr.ttl.atlasdd.mapper.user;

import fr.ttl.atlasdd.apidto.user.CustomUserDetails;
import fr.ttl.atlasdd.sqldto.user.UserSqlDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserDetailsMapper {

    UserDetailsMapper INSTANCE = Mappers.getMapper(UserDetailsMapper.class);

    CustomUserDetails toUserDetails(UserSqlDto userSqlDto);
}
