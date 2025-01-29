package fr.ttl.atlasdd.sqldto.campaign;

import fr.ttl.atlasdd.sqldto.BaseSqlDto;
import fr.ttl.atlasdd.sqldto.UserSqlDto;
import fr.ttl.atlasdd.sqldto.custom.CustomCharacterSheetSqlDto;
import fr.ttl.atlasdd.sqldto.ogl5.CharacterSheetSqlDto;
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
@Table(name = "campaigns")
public class CampaignSqlDto extends BaseSqlDto {

    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    @ManyToOne
    @JoinColumn(name = "game_master_id")
    private UserSqlDto gameMaster;

    @ManyToMany
    @JoinTable(
            name = "campaigns_have_players",
            joinColumns = @JoinColumn(name = "campaign_id"),
            inverseJoinColumns = @JoinColumn(name = "player_id")
    )
    private List<UserSqlDto> campaignPlayers;

    @ManyToMany
    @JoinTable(
            name = "campaigns_have_ogl5_character_sheets",
            joinColumns = @JoinColumn(name = "campaign_id"),
            inverseJoinColumns = @JoinColumn(name = "ogl5_character_sheet_id")
    )
    private List<CharacterSheetSqlDto> campaignOgl5CharacterSheets;

    @ManyToMany
    @JoinTable(
            name = "campaigns_have_custom_character_sheets",
            joinColumns = @JoinColumn(name = "campaign_id"),
            inverseJoinColumns = @JoinColumn(name = "custom_character_sheet_id")
    )
    private List<CustomCharacterSheetSqlDto> campaignCustomCharacterSheets;
}
