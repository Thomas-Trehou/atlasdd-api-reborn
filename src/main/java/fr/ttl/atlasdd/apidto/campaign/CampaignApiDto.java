package fr.ttl.atlasdd.apidto.campaign;

import fr.ttl.atlasdd.apidto.BaseApiDto;
import fr.ttl.atlasdd.apidto.UserLightApiDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class CampaignApiDto extends BaseApiDto {

    private String name;
    private String description;

    private UserLightApiDto gameMaster;
    private List<UserLightApiDto> campaignPlayers;
}
