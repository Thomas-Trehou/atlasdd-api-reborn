package fr.ttl.atlasdd.mapper.user;

import fr.ttl.atlasdd.apidto.user.UserLightApiDto;
import fr.ttl.atlasdd.entity.user.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserLightMapper {

    UserLightMapper INSTANCE = Mappers.getMapper(UserLightMapper.class);

    UserLightApiDto toApiDto(User user);

    User toSqlDto(UserLightApiDto userLightApiDto);

    void updateSqlDto(UserLightApiDto userLightApiDto, @MappingTarget User user);
}
