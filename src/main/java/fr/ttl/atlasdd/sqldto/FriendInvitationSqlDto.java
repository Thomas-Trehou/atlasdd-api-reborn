package fr.ttl.atlasdd.sqldto;

import fr.ttl.atlasdd.utils.InvitationStatus;
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
public class FriendInvitationSqlDto extends BaseSqlDto{

    @ManyToOne
    @JoinColumn(name = "request_user_id")
    private UserSqlDto requestUser;

    @ManyToOne
    @JoinColumn(name = "receiver_user_id")
    private UserSqlDto receiverUser;

    @Enumerated(EnumType.STRING)
    private InvitationStatus status;
}
