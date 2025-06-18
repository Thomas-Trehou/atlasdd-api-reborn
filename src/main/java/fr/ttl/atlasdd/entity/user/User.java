package fr.ttl.atlasdd.entity.user;

import fr.ttl.atlasdd.entity.BaseEntity;
import fr.ttl.atlasdd.entity.campaign.Campaign;
import fr.ttl.atlasdd.entity.character.custom.CustomCharacterSheet;
import fr.ttl.atlasdd.entity.character.ogl5.Ogl5CharacterSheet;
import fr.ttl.atlasdd.utils.user.UserState;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User extends BaseEntity {

    private String pseudo;
    private String slug;
    private String email;
    private String password;
    private String verificationToken;

    @Enumerated(EnumType.STRING)
    private UserState state = UserState.ACTIVE;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Ogl5CharacterSheet> characterSheetList;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<CustomCharacterSheet> customCharacterSheetList;

    @ManyToMany
    @JoinTable(
            name = "users_have_friends",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "friend_id")
    )
    private List<User> friends;

    @ManyToMany(mappedBy = "campaignPlayers")
    private List<Campaign> campaignsAsPlayer;

    @OneToMany(mappedBy = "gameMaster")
    private List<Campaign> campaignsAsGameMaster;

    @OneToMany(mappedBy = "requestUser", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<FriendInvitation> sentFriendInvitations;

    @OneToMany(mappedBy = "receiverUser", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<FriendInvitation> receivedFriendInvitations;

}
