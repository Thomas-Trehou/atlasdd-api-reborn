package fr.ttl.atlasdd.apidto.character.custom;

import com.fasterxml.jackson.annotation.JsonProperty;
import fr.ttl.atlasdd.apidto.character.ArmorApiDto;
import fr.ttl.atlasdd.apidto.character.BackgroundApiDto;
import fr.ttl.atlasdd.apidto.character.WeaponApiDto;
import fr.ttl.atlasdd.utils.character.Alignment;
import fr.ttl.atlasdd.utils.character.CharacterStatus;
import fr.ttl.atlasdd.utils.character.ShieldType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomCharacterSheetCreateRequestApiDto {

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
    @Enumerated(EnumType.STRING)
    private ShieldType shield;

    @NotNull
    private boolean twoWeaponsFighting;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Alignment alignment;

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
    @Enumerated(EnumType.STRING)
    private CharacterStatus status;

    @NotNull
    private Long userId;

    @NotNull
    private CustomRaceApiDto race;

    @NotNull
    private BackgroundApiDto background;

    @NotNull
    private CustomClassApiDto classe;

    @NotNull
    @JsonProperty("skillIds")
    private List<Long> skillIds;

    @JsonProperty("preparedSpellIds")
    private List<Long> preparedSpellIds;

    @NotNull
    private List<WeaponApiDto> weapons;

    @NotNull
    private ArmorApiDto armor;
}
