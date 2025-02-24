package fr.ttl.atlasdd.sqldto.user;

import fr.ttl.atlasdd.sqldto.BaseSqlDto;
import fr.ttl.atlasdd.sqldto.campaign.CampaignSqlDto;
import fr.ttl.atlasdd.sqldto.character.custom.CustomCharacterSheetSqlDto;
import fr.ttl.atlasdd.sqldto.character.ogl5.CharacterSheetSqlDto;
import fr.ttl.atlasdd.utils.user.UserState;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table(name = "users")
public class UserSqlDto extends BaseSqlDto {

    private String pseudo;
    private String slug;
    private String email;
    private String password;

    @Enumerated(EnumType.STRING)
    private UserState state = UserState.ACTIVE;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<CharacterSheetSqlDto> characterSheetList;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<CustomCharacterSheetSqlDto> customCharacterSheetList;

    @ManyToMany
    @JoinTable(
            name = "users_have_friends",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "friend_id")
    )
    private List<UserSqlDto> friends;

    @ManyToMany(mappedBy = "campaignPlayers")
    private List<CampaignSqlDto> campaignsAsPlayer;

    @OneToMany(mappedBy = "gameMaster")
    private List<CampaignSqlDto> campaignsAsGameMaster;

    @OneToMany(mappedBy = "requestUser", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<FriendInvitationSqlDto> sentFriendInvitations;

    @OneToMany(mappedBy = "receiverUser", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<FriendInvitationSqlDto> receivedFriendInvitations;

}
