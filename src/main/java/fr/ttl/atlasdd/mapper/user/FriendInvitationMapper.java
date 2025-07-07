package fr.ttl.atlasdd.mapper.user;

import fr.ttl.atlasdd.apidto.user.FriendInvitationApiDto;
import fr.ttl.atlasdd.entity.user.FriendInvitation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface FriendInvitationMapper {

    @Mapping(target = "receiver_user_id", source = "receiverUser.id")
    @Mapping(target = "requester_user_id", source = "requestUser.id")
    @Mapping(target = "receiver_user_pseudo", source = "receiverUser.pseudo")
    @Mapping(target = "requester_user_pseudo", source = "requestUser.pseudo")
    FriendInvitationApiDto toApiDto(FriendInvitation friendInvitation);
}
