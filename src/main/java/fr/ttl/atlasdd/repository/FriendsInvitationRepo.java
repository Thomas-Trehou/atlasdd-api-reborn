package fr.ttl.atlasdd.repository;

import fr.ttl.atlasdd.sqldto.FriendInvitationSqlDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FriendsInvitationRepo extends JpaRepository<FriendInvitationSqlDto, Long> {

    boolean existsByRequestUser_IdAndReceiverUser_Id(Long senderId, Long receiverId);

    FriendInvitationSqlDto findByIdAndReceiverUser_Id(Long id, Long receiverId);

    FriendInvitationSqlDto findByIdAndRequestUser_Id(Long id, Long senderId);
}
