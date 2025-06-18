package fr.ttl.atlasdd.apidto.character.custom;

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
public class CustomRaceApiDto extends BaseApiDto {

    @NotNull
    @Size(min = 2, max = 50, message = "Le nom de la race doit contenir entre 2 et 50 caractères")
    private String name;

    @NotNull
    @Size(max = 10, message = "La vitesse ne peut pas faire plus de 10 caractères")
    private String speed;

    @NotNull
    @Size(max = 100, message = "Les langues ne peuvent dépasser 100 caractères")
    private String languages;

    @NotNull
    @Size(max = 300, message = "Les traits ne peuvent dépasser 350 caractères")
    private String traits;
}
