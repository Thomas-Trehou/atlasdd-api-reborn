package fr.ttl.atlasdd.apidto.campaign;

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
public class CampaignCreateRequestApiDto extends BaseApiDto {

    @NotNull
    @Size(max = 75, message = "Le nom de la campagne doit contenir maximum 50 caractères")
    private String name;

    @NotNull
    @Size(max = 350, message = "La description de la campagne doit contenir maximum 350 caractères")
    private String description;

    @NotNull
    private Long gameMasterId;
}
