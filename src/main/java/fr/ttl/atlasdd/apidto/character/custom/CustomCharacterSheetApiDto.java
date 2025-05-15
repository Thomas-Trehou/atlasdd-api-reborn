package fr.ttl.atlasdd.apidto.character.custom;

import fr.ttl.atlasdd.apidto.BaseApiDto;
import fr.ttl.atlasdd.apidto.character.*;
import fr.ttl.atlasdd.apidto.user.UserLightApiDto;
import fr.ttl.atlasdd.utils.character.Alignment;
import fr.ttl.atlasdd.utils.character.CharacterStatus;
import fr.ttl.atlasdd.utils.character.ShieldType;
import fr.ttl.atlasdd.utils.character.SpellSlots;
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
public class CustomCharacterSheetApiDto extends BaseApiDto {

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

    private SpellSlots spellSlots;

    private int strengthSavingThrowBonus;
    private int dexteritySavingThrowBonus;
    private int constitutionSavingThrowBonus;
    private int intelligenceSavingThrowBonus;
    private int wisdomSavingThrowBonus;
    private int charismaSavingThrowBonus;

    @Enumerated(EnumType.STRING)
    private CharacterStatus status;

    private UserLightApiDto owner;
    private CustomRaceApiDto race;
    private BackgroundApiDto background;
    private CustomClassApiDto classe;
    private List<SkillApiDto> skills;
    private List<SpellApiDto> preparedSpells;
    private List<WeaponApiDto> weapons;
    private ArmorApiDto armor;
}
