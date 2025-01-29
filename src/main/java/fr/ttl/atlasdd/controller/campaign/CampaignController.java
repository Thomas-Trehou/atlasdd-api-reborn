package fr.ttl.atlasdd.controller.campaign;

import fr.ttl.atlasdd.apidto.campaign.CampaignApiDto;
import fr.ttl.atlasdd.apidto.campaign.CampaignCreateRequestApiDto;
import fr.ttl.atlasdd.service.campaign.CampaignService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/campaigns")
public class CampaignController {

    private final CampaignService campaignService;

    public CampaignController(CampaignService campaignService) {
        this.campaignService = campaignService;
    }

    @PostMapping
    public CampaignApiDto createCampaign(@RequestBody CampaignCreateRequestApiDto campaignCreateRequestApiDto) {
        return campaignService.createCampaign(campaignCreateRequestApiDto);
    }

    @GetMapping("/{id}")
    public CampaignApiDto getCampaign(@PathVariable Long id) {
        return campaignService.getCampaignById(id);
    }

    @PatchMapping("/{id}")
    public CampaignApiDto updateCampaign(@PathVariable Long id,@RequestBody CampaignCreateRequestApiDto campaignCreateRequestApiDto) {
        return campaignService.updateCampaign(id, campaignCreateRequestApiDto);
    }

    @PatchMapping("/{id}/add-player/{playerId}")
    public CampaignApiDto addPlayerToCampaign(@PathVariable Long id, @PathVariable Long playerId) {
        return campaignService.addPlayerToCampaign(id, playerId);
    }

    @PatchMapping("/{id}/remove-player/{playerId}")
    public CampaignApiDto removePlayerFromCampaign(@PathVariable Long id, @PathVariable Long playerId) {
        return campaignService.removePlayerFromCampaign(id, playerId);
    }

    @PatchMapping("/{id}/add-ogl5-character/{characterId}")
    public CampaignApiDto addOgl5CharacterToCampaign(@PathVariable Long id, @PathVariable Long characterId) {
        return campaignService.addOgl5CharacterToCampaign(id, characterId);
    }

    @PatchMapping("/{id}/remove-ogl5-character/{characterId}")
    public CampaignApiDto removeOgl5CharacterFromCampaign(@PathVariable Long id, @PathVariable Long characterId) {
        return campaignService.removeOgl5CharacterFromCampaign(id, characterId);
    }

    @PatchMapping("/{id}/add-custom-character/{characterId}")
    public CampaignApiDto addCustomCharacterToCampaign(@PathVariable Long id, @PathVariable Long characterId) {
        return campaignService.addCustomCharacterToCampaign(id, characterId);
    }

    @PatchMapping("/{id}/remove-custom-character/{characterId}")
    public CampaignApiDto removeCustomCharacterFromCampaign(@PathVariable Long id, @PathVariable Long characterId) {
        return campaignService.removeCustomCharacterFromCampaign(id, characterId);
    }

    @DeleteMapping("/{id}")
    public void deleteCampaign(@PathVariable Long id) {
        campaignService.deleteCampaign(id);
    }

}
