package fr.ttl.atlasdd.apidto.character;

import fr.ttl.atlasdd.apidto.BaseApiDto;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class WeaponApiDto extends BaseApiDto {

    @NotNull
    @Size(max = 50, message = "L'index de l'arme doit contenir maximum 50 caractères")
    private String index;

    @NotNull
    @Size(max = 50, message = "Le nom de l'arme doit contenir maximum 50 caractères")
    private String name;

    @NotNull
    @Size(max = 20, message = "La porté de l'arme doit contenir maximum 20 caractères")
    private String weaponRange;

    @Size(max = 50, message = "Le coût ne peut pas dépasser 50 caractères")
    private String cost;

    @NotNull
    @Size(max = 20, message = "Les dès de dégats de l'arme ne peuvent dépasser 20 caractères")
    private String damageDice;

    @NotNull
    @Size(max = 50, message = "Les types de dégats de l'arme ne peuvent dépasser 50 caractères")
    private String damageType;

    @Max(1000)
    private BigDecimal weight;

    @Size(max = 350, message = "Les propriétés ne peuvent contenir plus de 350 caractères")
    private String properties;
}
