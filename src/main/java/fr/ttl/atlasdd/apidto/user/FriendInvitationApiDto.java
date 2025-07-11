package fr.ttl.atlasdd.apidto.user;

import fr.ttl.atlasdd.apidto.BaseApiDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class FriendInvitationApiDto extends BaseApiDto {

    private int receiver_user_id;
    private String receiver_user_pseudo;
    private int requester_user_id;
    private String requester_user_pseudo;
}
