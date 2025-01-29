package fr.ttl.atlasdd.service.campaign;

import fr.ttl.atlasdd.apidto.campaign.CampaignApiDto;
import fr.ttl.atlasdd.apidto.campaign.CampaignCreateRequestApiDto;
import org.springframework.stereotype.Service;

@Service
public interface CampaignService {

    CampaignApiDto createCampaign(CampaignCreateRequestApiDto campaignCreateRequestApiDto);

    CampaignApiDto getCampaignById(Long id);

    CampaignApiDto updateCampaign(Long id, CampaignCreateRequestApiDto campaignCreateRequestApiDto);

    CampaignApiDto addPlayerToCampaign(Long campaignId, Long playerId);

    CampaignApiDto removePlayerFromCampaign(Long campaignId, Long playerId);

    CampaignApiDto addOgl5CharacterToCampaign(Long campaignId, Long characterId);

    CampaignApiDto removeOgl5CharacterFromCampaign(Long campaignId, Long characterId);

    CampaignApiDto addCustomCharacterToCampaign(Long campaignId, Long characterId);

    CampaignApiDto removeCustomCharacterFromCampaign(Long campaignId, Long characterId);

    void deleteCampaign(Long id);

}
