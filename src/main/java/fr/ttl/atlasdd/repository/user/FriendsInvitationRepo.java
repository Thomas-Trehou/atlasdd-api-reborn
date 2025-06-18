package fr.ttl.atlasdd.repository.user;

import fr.ttl.atlasdd.entity.user.FriendInvitation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FriendsInvitationRepo extends JpaRepository<FriendInvitation, Long> {

    boolean existsByRequestUser_IdAndReceiverUser_Id(Long senderId, Long receiverId);

    FriendInvitation findByIdAndReceiverUser_Id(Long id, Long receiverId);

    FriendInvitation findByIdAndRequestUser_Id(Long id, Long senderId);
}
