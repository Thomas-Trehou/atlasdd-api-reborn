package fr.ttl.atlasdd.apidto.character.ogl5;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CharacterSheetCreateRequestApiDto {

    @NotNull
    private String name;

    @NotNull
    private int level;

    private int experience;

    @NotNull
    private int armorClass;

    @NotNull
    private int initiative;

    @NotNull
    private int inspiration;

    @NotNull
    private int hitPoints;

    @NotNull
    private int maxHitPoints;

    private int bonusHitPoints;

    @NotNull
    private int speed;

    @NotNull
    private int passivePerception;

    @NotNull
    private String shield;

    @NotNull
    private boolean twoWeaponsFighting;

    @NotNull
    private String alignment;

    @NotNull
    private int strength;

    @NotNull
    private int dexterity;

    @NotNull
    private int constitution;

    @NotNull
    private int intelligence;

    @NotNull
    private int wisdom;

    @NotNull
    private int charisma;

    @NotNull
    private String status;

    @NotNull
    private Long userId;

    @NotNull
    private Long raceId;

    @NotNull
    private Long backgroundId;

    @NotNull
    private Long classId;

    @NotNull
    @JsonProperty("skillIds")
    private List<Long> skillIds;

    @JsonProperty("preparedSpellIds")
    private List<Long> preparedSpellIds;

    @NotNull
    @JsonProperty("weaponIds")
    private List<Long> weaponIds;

    @NotNull
    private Long armorId;
}
