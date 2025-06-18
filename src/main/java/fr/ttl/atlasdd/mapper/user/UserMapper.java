package fr.ttl.atlasdd.mapper.user;

import fr.ttl.atlasdd.apidto.user.UserApiDto;
import fr.ttl.atlasdd.entity.user.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserApiDto toApiDto(User user);

    @Mapping(target = "state", ignore = true)
    @Mapping(target = "sentFriendInvitations", ignore = true)
    @Mapping(target = "receivedFriendInvitations", ignore = true)
    @Mapping(target = "friends", ignore = true)
    @Mapping(target = "customCharacterSheetList", ignore = true)
    @Mapping(target = "characterSheetList", ignore = true)
    @Mapping(target = "campaignsAsPlayer", ignore = true)
    @Mapping(target = "campaignsAsGameMaster", ignore = true)
    User toEntity(UserApiDto userApiDto);

    @Mapping(target = "state", ignore = true)
    @Mapping(target = "sentFriendInvitations", ignore = true)
    @Mapping(target = "receivedFriendInvitations", ignore = true)
    @Mapping(target = "friends", ignore = true)
    @Mapping(target = "customCharacterSheetList", ignore = true)
    @Mapping(target = "characterSheetList", ignore = true)
    @Mapping(target = "campaignsAsPlayer", ignore = true)
    @Mapping(target = "campaignsAsGameMaster", ignore = true)
    void updateSqlDto(UserApiDto userApiDto, @MappingTarget User user);
}
