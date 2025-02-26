package fr.ttl.atlasdd.entity.campaign;

import fr.ttl.atlasdd.entity.BaseEntity;
import fr.ttl.atlasdd.entity.character.custom.CustomCharacterSheet;
import fr.ttl.atlasdd.entity.character.ogl5.Ogl5CharacterSheet;
import fr.ttl.atlasdd.entity.user.User;
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
public class Campaign extends BaseEntity {

    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    @ManyToOne
    @JoinColumn(name = "game_master_id")
    private User gameMaster;

    @ManyToMany
    @JoinTable(
            name = "campaigns_have_players",
            joinColumns = @JoinColumn(name = "campaign_id"),
            inverseJoinColumns = @JoinColumn(name = "player_id")
    )
    private List<User> campaignPlayers;

    @ManyToMany
    @JoinTable(
            name = "campaigns_have_ogl5_character_sheets",
            joinColumns = @JoinColumn(name = "campaign_id"),
            inverseJoinColumns = @JoinColumn(name = "ogl5_character_sheet_id")
    )
    private List<Ogl5CharacterSheet> campaignOgl5CharacterSheets;

    @ManyToMany
    @JoinTable(
            name = "campaigns_have_custom_character_sheets",
            joinColumns = @JoinColumn(name = "campaign_id"),
            inverseJoinColumns = @JoinColumn(name = "custom_character_sheet_id")
    )
    private List<CustomCharacterSheet> campaignCustomCharacterSheets;

    @OneToMany(mappedBy = "campaign", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<CampaignNote> campaignNotes;
}
