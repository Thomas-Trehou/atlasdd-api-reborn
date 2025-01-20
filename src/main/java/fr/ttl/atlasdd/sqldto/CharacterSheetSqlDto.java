package fr.ttl.atlasdd.sqldto;

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
@Table(name = "character_sheets")
public class CharacterSheetSqlDto extends BaseSqlDto {

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
    private RaceSqlDto race;

    @ManyToOne
    @JoinColumn(name = "background_id")
    private BackgroundSqlDto background;

    @ManyToOne
    @JoinColumn(name = "class_id")
    private ClassSqlDto classe;

    @ManyToMany
    @JoinTable(
            name = "character_sheets_has_skills",
            joinColumns = @JoinColumn(name = "character_id"),
            inverseJoinColumns = @JoinColumn(name = "skill_id")
    )
    private List<SkillSqlDto> skills;

    @ManyToMany
    @JoinTable(
            name = "character_prepared_spells",
            joinColumns = @JoinColumn(name = "character_id"),
            inverseJoinColumns = @JoinColumn(name = "spell_id")
    )
    private List<SpellSqlDto> preparedSpells;

    @ManyToMany
    @JoinTable(
            name = "character_sheets_has_weapons",
            joinColumns = @JoinColumn(name = "character_id"),
            inverseJoinColumns = @JoinColumn(name = "weapon_id")
    )
    private List<WeaponSqlDto> weapons;

    @ManyToOne
    @JoinColumn(name = "armor_id")
    private ArmorSqlDto armor;

    @ManyToMany
    @JoinTable(
            name = "character_sheets_has_notes",
            joinColumns = @JoinColumn(name = "character_id"),
            inverseJoinColumns = @JoinColumn(name = "characterNote_id")
    )
    private List<NoteCharacterSqlDto> characterNotes;

}
