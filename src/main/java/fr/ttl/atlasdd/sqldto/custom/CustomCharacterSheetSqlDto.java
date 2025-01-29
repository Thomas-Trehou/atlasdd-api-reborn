package fr.ttl.atlasdd.sqldto.custom;

import fr.ttl.atlasdd.sqldto.BaseSqlDto;
import fr.ttl.atlasdd.sqldto.campaign.CampaignSqlDto;
import fr.ttl.atlasdd.sqldto.NoteCharacterSqlDto;
import fr.ttl.atlasdd.sqldto.UserSqlDto;
import fr.ttl.atlasdd.utils.Alignment;
import fr.ttl.atlasdd.utils.CharacterStatus;
import fr.ttl.atlasdd.utils.ShieldType;
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
@Table(name = "custom_character_sheets")
public class CustomCharacterSheetSqlDto extends BaseSqlDto {

    private String name;
    private int level;
    private int experience;
    private int armorClass;
    private int initiative;
    private int inspiration;
    private int hitPoints;
    private int maxHitPoints;
    private int bonusHitPoints;
    private int speed;
    private int passivePerception;

    @Enumerated(EnumType.STRING)
    private ShieldType shield;

    private boolean twoWeaponsFighting;

    @Enumerated(EnumType.STRING)
    private Alignment alignment;

    private int strength;
    private int dexterity;
    private int constitution;
    private int intelligence;
    private int wisdom;
    private int charisma;

    @Enumerated(EnumType.STRING)
    private CharacterStatus status;


    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserSqlDto owner;

    @ManyToOne
    @JoinColumn(name = "race_id")
    private CustomRaceSqlDto race;

    @ManyToOne
    @JoinColumn(name = "background_id")
    private CustomBackgroundSqlDto background;

    @ManyToOne
    @JoinColumn(name = "class_id")
    private CustomClassSqlDto classe;

    @ManyToMany
    @JoinTable(
            name = "custom_character_sheets_has_skills",
            joinColumns = @JoinColumn(name = "character_id"),
            inverseJoinColumns = @JoinColumn(name = "skill_id")
    )
    private List<CustomSkillSqlDto> skills;

    @ManyToMany
    @JoinTable(
            name = "custom_character_prepared_spells",
            joinColumns = @JoinColumn(name = "character_id"),
            inverseJoinColumns = @JoinColumn(name = "spell_id")
    )
    private List<CustomSpellSqlDto> preparedSpells;

    @ManyToMany
    @JoinTable(
            name = "custom_character_sheets_has_weapons",
            joinColumns = @JoinColumn(name = "character_id"),
            inverseJoinColumns = @JoinColumn(name = "weapon_id")
    )
    private List<CustomWeaponSqlDto> weapons;

    @ManyToOne
    @JoinColumn(name = "armor_id")
    private CustomArmorSqlDto armor;

    @OneToMany(mappedBy = "customCharacterSheet")
    private List<NoteCharacterSqlDto> characterNotes;

    @ManyToMany(mappedBy = "campaignCustomCharacterSheets")
    private List<CampaignSqlDto> campaigns;

}
