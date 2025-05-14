package fr.ttl.atlasdd.entity.character.ogl5;

import fr.ttl.atlasdd.entity.BaseEntity;
import fr.ttl.atlasdd.entity.campaign.Campaign;
import fr.ttl.atlasdd.entity.character.CharacterNote;
import fr.ttl.atlasdd.entity.user.User;
import fr.ttl.atlasdd.utils.character.Alignment;
import fr.ttl.atlasdd.utils.character.CharacterStatus;
import fr.ttl.atlasdd.utils.character.ShieldType;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ogl5_character_sheets")
public class Ogl5CharacterSheet extends BaseEntity {

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


    @ManyToOne
    @JoinColumn(name = "user_id")
    private User owner;

    @ManyToOne
    @JoinColumn(name = "race_id")
    private Ogl5Race race;

    @ManyToOne
    @JoinColumn(name = "background_id")
    private Ogl5Background background;

    @ManyToOne
    @JoinColumn(name = "class_id")
    private Ogl5Class classe;

    @ManyToMany
    @JoinTable(
            name = "ogl5_character_sheets_has_skills",
            joinColumns = @JoinColumn(name = "character_id"),
            inverseJoinColumns = @JoinColumn(name = "skill_id")
    )
    private List<Ogl5Skill> skills;

    @ManyToMany
    @JoinTable(
            name = "ogl5_character_prepared_spells",
            joinColumns = @JoinColumn(name = "character_id"),
            inverseJoinColumns = @JoinColumn(name = "spell_id")
    )
    private List<Ogl5Spell> preparedSpells;

    @ManyToMany
    @JoinTable(
            name = "ogl5_character_sheets_has_weapons",
            joinColumns = @JoinColumn(name = "character_id"),
            inverseJoinColumns = @JoinColumn(name = "weapon_id")
    )
    private List<Ogl5Weapon> weapons;

    @ManyToOne
    @JoinColumn(name = "armor_id")
    private Ogl5Armor armor;

    @OneToMany(mappedBy = "ogl5CharacterSheet", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CharacterNote> characterNotes;

    @ManyToMany(mappedBy = "campaignOgl5CharacterSheets")
    private List<Campaign> campaigns;

}
