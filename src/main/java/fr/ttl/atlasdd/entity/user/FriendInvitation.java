package fr.ttl.atlasdd.entity.user;

import fr.ttl.atlasdd.entity.BaseEntity;
import fr.ttl.atlasdd.utils.user.InvitationStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table(name = "friend_invitations")
public class FriendInvitation extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "request_user_id")
    private User requestUser;

    @ManyToOne
    @JoinColumn(name = "receiver_user_id")
    private User receiverUser;

    @Enumerated(EnumType.STRING)
    private InvitationStatus status;
}
