package fr.ttl.atlasdd.apidto.character.custom;

import fr.ttl.atlasdd.apidto.BaseApiDto;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class CustomClassApiDto extends BaseApiDto {

    @NotNull
    @Size(min = 2, max = 50, message = "Le nom de la classe doit contenir entre 2 et 50 caractères")
    private String name;

    @NotNull
    private String hitDice;

    @NotNull
    @PositiveOrZero
    private int startingHitPoints;

    @NotNull
    @Size(max = 350, message = "L'équipement de départ doit contenir entre 0 et 350 caractères")
    private String startingEquipment;
}
