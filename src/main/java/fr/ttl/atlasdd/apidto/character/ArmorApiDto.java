package fr.ttl.atlasdd.apidto.character;

import fr.ttl.atlasdd.apidto.BaseApiDto;
import fr.ttl.atlasdd.utils.character.ArmorCategory;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
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
public class ArmorApiDto extends BaseApiDto {

    @NotNull
    @Size(max = 50, message = "L'index de l'armure doit contenir maximum 50 caractères")
    private String index;

    @NotNull
    @Size(max = 50, message = "Le nom de l'armure doit contenir maximum 50 caractères")
    private String name;

    @NotNull
    @Enumerated(EnumType.STRING)
    private ArmorCategory armorCategory;

    @NotNull
    @PositiveOrZero
    private int armorClass;

    @PositiveOrZero
    private int strengthMinimum;

    @NotNull
    private boolean stealthDisadvantage;

    @Max(1000)
    private BigDecimal weight;

    @Size(max = 50, message = "Le coût ne peut pas dépasser 50 caractères")
    private String cost;

    @Size(max = 350, message = "Les propriétés ne peuvent contenir plus de 350 caractères")
    private String properties;
}
