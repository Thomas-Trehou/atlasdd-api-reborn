package fr.ttl.atlasdd.apidto.ogl5;

import com.fasterxml.jackson.annotation.JsonProperty;
import fr.ttl.atlasdd.apidto.BaseApiDto;
import fr.ttl.atlasdd.apidto.NoteCharacterApiDto;
import fr.ttl.atlasdd.apidto.UserApiDto;
import fr.ttl.atlasdd.utils.Alignment;
import fr.ttl.atlasdd.utils.CharacterStatus;
import fr.ttl.atlasdd.utils.ShieldType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class CharacterSheetApiDto extends BaseApiDto {

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

    private UserApiDto owner;
    private RaceApiDto race;
    private BackgroundApiDto background;
    private ClassApiDto classe;
    private List<SkillApiDto> skills;
    private List<SpellApiDto> preparedSpells;
    private List<WeaponApiDto> weapons;
    private ArmorApiDto armor;
}
