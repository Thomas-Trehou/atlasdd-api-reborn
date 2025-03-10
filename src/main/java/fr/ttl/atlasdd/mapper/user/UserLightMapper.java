package fr.ttl.atlasdd.mapper.user;

import fr.ttl.atlasdd.apidto.user.UserLightApiDto;
import fr.ttl.atlasdd.entity.user.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserLightMapper {

    UserLightApiDto toApiDto(User user);

    @Mapping(target = "password", ignore = true)
    @Mapping(target = "state", ignore = true)
    @Mapping(target = "sentFriendInvitations", ignore = true)
    @Mapping(target = "receivedFriendInvitations", ignore = true)
    @Mapping(target = "friends", ignore = true)
    @Mapping(target = "customCharacterSheetList", ignore = true)
    @Mapping(target = "characterSheetList", ignore = true)
    @Mapping(target = "campaignsAsPlayer", ignore = true)
    @Mapping(target = "campaignsAsGameMaster", ignore = true)
    User toEntity(UserLightApiDto userLightApiDto);

    @Mapping(target = "password", ignore = true)
    @Mapping(target = "state", ignore = true)
    @Mapping(target = "sentFriendInvitations", ignore = true)
    @Mapping(target = "receivedFriendInvitations", ignore = true)
    @Mapping(target = "friends", ignore = true)
    @Mapping(target = "customCharacterSheetList", ignore = true)
    @Mapping(target = "characterSheetList", ignore = true)
    @Mapping(target = "campaignsAsPlayer", ignore = true)
    @Mapping(target = "campaignsAsGameMaster", ignore = true)
    void updateSqlDto(UserLightApiDto userLightApiDto, @MappingTarget User user);
}
