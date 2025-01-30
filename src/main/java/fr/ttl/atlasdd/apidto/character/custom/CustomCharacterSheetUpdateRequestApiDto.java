package fr.ttl.atlasdd.apidto.character.custom;

import com.fasterxml.jackson.annotation.JsonProperty;
import fr.ttl.atlasdd.utils.character.CharacterStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

import java.util.List;

@Data
public class CustomCharacterSheetUpdateRequestApiDto {

    private Long id;
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
    private String shield;
    private boolean twoWeaponsFighting;
    private String alignment;
    private int strength;
    private int dexterity;
    private int constitution;
    private int intelligence;
    private int wisdom;
    private int charisma;

    @Enumerated(EnumType.STRING)
    private CharacterStatus status;

    private Long userId;
    private CustomRaceApiDto race;
    private CustomBackgroundApiDto background;
    private CustomClassApiDto classe;

    @JsonProperty("skillIds")
    private List<Long> skillIds;

    @JsonProperty("preparedSpellIds")
    private List<Long> preparedSpellIds;

    private List<CustomWeaponApiDto> weapons;
    private CustomArmorApiDto armor;
}
