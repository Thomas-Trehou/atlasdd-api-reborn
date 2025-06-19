package fr.ttl.atlasdd.entity.character.custom;

import fr.ttl.atlasdd.entity.BaseEntity;
import fr.ttl.atlasdd.entity.campaign.Campaign;
import fr.ttl.atlasdd.entity.character.CharacterNote;
import fr.ttl.atlasdd.entity.character.ogl5.Ogl5CharacterSheet;
import fr.ttl.atlasdd.entity.character.ogl5.Ogl5CharacterSkill;
import fr.ttl.atlasdd.entity.user.User;
import fr.ttl.atlasdd.utils.character.*;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "custom_character_sheets")
public class CustomCharacterSheet extends BaseEntity {

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

    private int strengthSavingThrowBonus = 0;
    private int dexteritySavingThrowBonus = 0;
    private int constitutionSavingThrowBonus = 0;
    private int intelligenceSavingThrowBonus = 0;
    private int wisdomSavingThrowBonus = 0;
    private int charismaSavingThrowBonus = 0;

    @Enumerated(EnumType.STRING)
    private CharacterStatus status;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "spell_slots", columnDefinition = "jsonb")
    private SpellSlots spellSlots;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User owner;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "race_id")
    private CustomRace race;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "background_id")
    private CustomBackground background;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "class_id")
    private CustomClass classe;

    @ManyToMany
    @JoinTable(
            name = "custom_character_sheets_has_skills",
            joinColumns = @JoinColumn(name = "character_id"),
            inverseJoinColumns = @JoinColumn(name = "skill_id")
    )
    private List<CustomSkill> skills;

    @OneToMany(mappedBy = "characterSheet", cascade = CascadeType.ALL)
    private List<CustomCharacterSkill> characterSkills;

    public List<CustomCharacterSheet> getCharacterSheets() {
        return characterSkills != null ?
                characterSkills.stream().map(CustomCharacterSkill::getCharacterSheet).toList() :
                List.of();
    }

    @ManyToMany
    @JoinTable(
            name = "custom_character_prepared_spells",
            joinColumns = @JoinColumn(name = "character_id"),
            inverseJoinColumns = @JoinColumn(name = "spell_id")
    )
    private List<CustomSpell> preparedSpells;

    @ManyToMany(cascade = CascadeType.REMOVE)
    @JoinTable(
            name = "custom_character_sheets_has_weapons",
            joinColumns = @JoinColumn(name = "character_id"),
            inverseJoinColumns = @JoinColumn(name = "weapon_id")
    )
    private List<CustomWeapon> weapons;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "armor_id")
    private CustomArmor armor;

    @OneToMany(mappedBy = "customCharacterSheet", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CharacterNote> characterNotes;

    @ManyToMany(mappedBy = "campaignCustomCharacterSheets")
    private List<Campaign> campaigns;

}
