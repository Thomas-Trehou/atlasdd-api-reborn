package fr.ttl.atlasdd.mapper.user;

import fr.ttl.atlasdd.apidto.user.UserLightApiDto;
import fr.ttl.atlasdd.sqldto.user.UserSqlDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserLightMapper {

    UserLightMapper INSTANCE = Mappers.getMapper(UserLightMapper.class);

    UserLightApiDto toApiDto(UserSqlDto userSqlDto);

    UserSqlDto toSqlDto(UserLightApiDto userLightApiDto);

    void updateSqlDto(UserLightApiDto userLightApiDto, @MappingTarget UserSqlDto userSqlDto);
}
