package fr.ttl.atlasdd.apidto.custom;

import com.fasterxml.jackson.annotation.JsonProperty;
import fr.ttl.atlasdd.apidto.NoteCharacterApiDto;
import fr.ttl.atlasdd.apidto.UserApiDto;
import fr.ttl.atlasdd.utils.Alignment;
import fr.ttl.atlasdd.utils.CharacterStatus;
import fr.ttl.atlasdd.utils.ShieldType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

import java.util.List;

@Data
public class CustomCharacterSheetCreateRequestApiDto {
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
