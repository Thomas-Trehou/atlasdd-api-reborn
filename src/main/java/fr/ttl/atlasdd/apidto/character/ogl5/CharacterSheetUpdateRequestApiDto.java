package fr.ttl.atlasdd.apidto.character.ogl5;

import com.fasterxml.jackson.annotation.JsonProperty;
import fr.ttl.atlasdd.apidto.character.CharacterSkillDto;
import fr.ttl.atlasdd.utils.character.Alignment;
import fr.ttl.atlasdd.utils.character.CharacterStatus;
import fr.ttl.atlasdd.utils.character.ShieldType;
import fr.ttl.atlasdd.utils.character.SpellSlots;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CharacterSheetUpdateRequestApiDto {

    @NotNull
    private Long id;

    @NotBlank(message = "Le nom ne peut pas être vide")
    @Size(min = 2, max = 50, message = "Le nom doit contenir entre 2 et 50 caractères")
    private String name;

    @NotNull
    @Min(1) @Max(20)
    private int level;

    @NotNull
    @PositiveOrZero
    private int experience;

    @NotNull
    @Min(1)
    private int armorClass;

    @NotNull
    @PositiveOrZero
    private int initiative;

    @NotNull
    @PositiveOrZero
    private int inspiration;

    @NotNull
    @PositiveOrZero
    private int hitPoints;

    @NotNull
    @Min(1)
    private int maxHitPoints;

    @PositiveOrZero
    private int bonusHitPoints;

    @NotNull
    @Positive
    private int speed;

    @NotNull
    @PositiveOrZero
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
    @Min(1) @Max(30)
    private int strength;

    @NotNull
    @Min(1) @Max(30)
    private int dexterity;

    @NotNull
    @Min(1) @Max(30)
    private int constitution;

    @NotNull
    @Min(1) @Max(30)
    private int intelligence;

    @NotNull
    @Min(1) @Max(30)
    private int wisdom;

    @NotNull
    @Min(1) @Max(30)
    private int charisma;

    @NotNull
    @Min(0) @Max(30)
    private int strengthSavingThrowBonus;

    @NotNull
    @Min(0) @Max(30)
    private int dexteritySavingThrowBonus;

    @NotNull
    @Min(0) @Max(30)
    private int constitutionSavingThrowBonus;

    @NotNull
    @Min(0) @Max(30)
    private int intelligenceSavingThrowBonus;

    @NotNull
    @Min(0) @Max(30)
    private int wisdomSavingThrowBonus;

    @NotNull
    @Min(0) @Max(30)
    private int charismaSavingThrowBonus;

    @NotNull
    @Enumerated(EnumType.STRING)
    private CharacterStatus status;

    private SpellSlots spellSlots;

    @NotNull
    private Long userId;

    @NotNull
    private Long raceId;

    @NotNull
    private Long backgroundId;

    @NotNull
    private Long classId;

    @NotNull
    @NotEmpty(message = "Au moins une compétence doit être sélectionnée")
    @JsonProperty("skills")
    private List<CharacterSkillDto> skills;

    @JsonProperty("preparedSpellIds")
    private List<Long> preparedSpellIds;

    @NotNull
    @NotEmpty(message = "Au moins une arme doit être équipée")
    @JsonProperty("weaponIds")
    private List<Long> weaponIds;

    @NotNull
    private Long armorId;
}
