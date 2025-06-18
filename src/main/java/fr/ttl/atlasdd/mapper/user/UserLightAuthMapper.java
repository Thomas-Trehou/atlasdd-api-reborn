package fr.ttl.atlasdd.mapper.user;

import fr.ttl.atlasdd.apidto.user.UserLightAuthApiDto;
import fr.ttl.atlasdd.entity.user.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserLightAuthMapper {


    @Mapping(target = "token", ignore = true)
    UserLightAuthApiDto toApiDto(User user);

    @Mapping(target = "state", ignore = true)
    @Mapping(target = "password", ignore = true)
    @Mapping(target = "sentFriendInvitations", ignore = true)
    @Mapping(target = "receivedFriendInvitations", ignore = true)
    @Mapping(target = "friends", ignore = true)
    @Mapping(target = "customCharacterSheetList", ignore = true)
    @Mapping(target = "characterSheetList", ignore = true)
    @Mapping(target = "campaignsAsPlayer", ignore = true)
    @Mapping(target = "campaignsAsGameMaster", ignore = true)
    User toEntity(UserLightAuthApiDto userLightAuthApiDto);
}
