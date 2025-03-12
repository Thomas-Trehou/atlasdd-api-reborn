package fr.ttl.atlasdd.service.campaign;

import fr.ttl.atlasdd.apidto.campaign.CampaignApiDto;
import fr.ttl.atlasdd.apidto.campaign.CampaignCreateRequestApiDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CampaignService {

    CampaignApiDto createCampaign(CampaignCreateRequestApiDto campaignCreateRequestApiDto);

    CampaignApiDto getCampaignById(Long id);

    List<CampaignApiDto> getAllByUserId(Long userId);

    CampaignApiDto updateCampaign(Long id, CampaignCreateRequestApiDto campaignCreateRequestApiDto);

    CampaignApiDto addPlayerToCampaign(Long campaignId, Long playerId);

    CampaignApiDto removePlayerFromCampaign(Long campaignId, Long playerId);

    CampaignApiDto addOgl5CharacterToCampaign(Long campaignId, Long characterId);

    CampaignApiDto removeOgl5CharacterFromCampaign(Long campaignId, Long characterId);

    CampaignApiDto addCustomCharacterToCampaign(Long campaignId, Long characterId);

    CampaignApiDto removeCustomCharacterFromCampaign(Long campaignId, Long characterId);

    List<CampaignApiDto> getCampaignsAsPlayer(Long playerId);

    void deletePlayerFromCampaigns(List<CampaignApiDto> campaigns, Long playerId);

    void deleteCampaignsAsDungeonMaster(Long dungeonMasterId);

    void deleteCampaign(Long id);

}
