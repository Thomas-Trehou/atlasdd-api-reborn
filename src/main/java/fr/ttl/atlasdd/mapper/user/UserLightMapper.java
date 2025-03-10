package fr.ttl.atlasdd.mapper.user;

import fr.ttl.atlasdd.apidto.user.UserLightApiDto;
import fr.ttl.atlasdd.entity.user.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserLightMapper {

    UserLightApiDto toApiDto(User user);

    User toEntity(UserLightApiDto userLightApiDto);

    void updateSqlDto(UserLightApiDto userLightApiDto, @MappingTarget User user);
}
