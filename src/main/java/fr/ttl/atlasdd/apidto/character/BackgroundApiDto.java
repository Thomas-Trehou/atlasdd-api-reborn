package fr.ttl.atlasdd.apidto.character;

import fr.ttl.atlasdd.apidto.BaseApiDto;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class BackgroundApiDto  extends BaseApiDto {

    @NotNull
    @Size(max = 50, message = "Le nom du background doit contenir maximum 50 caractères")
    private String name;

    @NotNull
    @Size(max = 75, message = "Les outils maitrisés ne peuvent pas dépasser 50 caractères")
    private String masteredTools;

    @NotNull
    @Size(max = 250, message = "Les équipements de départ ne peuvent pas dépasser 250 caractères")
    private String startingEquipment;

    @NotNull
    @Size(max = 75, message = "Les traits du background ne peuvent dépasser 75 caractères")
    private String backgroundFeature;
}
