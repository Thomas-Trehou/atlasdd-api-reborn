package fr.ttl.atlasdd.apidto.campaign;

import fr.ttl.atlasdd.apidto.BaseApiDto;
import jakarta.validation.constraints.NotNull;
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
    private String name;

    @NotNull
    private String description;

    @NotNull
    private Long gameMasterId;
}
