package fr.ttl.atlasdd.sqldto.user;

import fr.ttl.atlasdd.sqldto.BaseSqlDto;
import fr.ttl.atlasdd.sqldto.campaign.CampaignSqlDto;
import fr.ttl.atlasdd.sqldto.character.ogl5.CharacterSheetSqlDto;
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

    @OneToMany(mappedBy = "owner")
    private List<CharacterSheetSqlDto> characterSheetList;

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

}
