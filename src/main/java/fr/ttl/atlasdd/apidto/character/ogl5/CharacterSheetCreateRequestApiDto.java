package fr.ttl.atlasdd.apidto.character.ogl5;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class CharacterSheetCreateRequestApiDto {
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
    private String status;
    private Long userId;
    private Long raceId;
    private Long backgroundId;
    private Long classId;

    @JsonProperty("skillIds")
    private List<Long> skillIds;

    @JsonProperty("preparedSpellIds")
    private List<Long> preparedSpellIds;

    @JsonProperty("weaponIds")
    private List<Long> weaponIds;

    private Long armorId;
}
