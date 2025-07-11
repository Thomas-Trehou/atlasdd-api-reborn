package fr.ttl.atlasdd.repository.user;

import fr.ttl.atlasdd.entity.user.FriendInvitation;
import fr.ttl.atlasdd.utils.user.InvitationStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FriendsInvitationRepo extends JpaRepository<FriendInvitation, Long> {

    boolean existsByRequestUser_IdAndReceiverUser_Id(Long senderId, Long receiverId);

    FriendInvitation findByIdAndReceiverUser_Id(Long id, Long receiverId);

    FriendInvitation findByIdAndRequestUser_Id(Long id, Long senderId);

    @Query("SELECT f FROM FriendInvitation f WHERE (f.requestUser.id = :userId OR f.receiverUser.id = :userId) AND f.status = :status")
    List<FriendInvitation> findByUserAndStatus(@Param("userId") Long userId, @Param("status") InvitationStatus status);
}
