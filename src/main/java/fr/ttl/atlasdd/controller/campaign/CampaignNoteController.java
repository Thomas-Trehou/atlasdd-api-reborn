package fr.ttl.atlasdd.controller.campaign;

import fr.ttl.atlasdd.apidto.campaign.CampaignNoteApiDto;
import fr.ttl.atlasdd.service.campaign.CampaignNoteService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/campaign-notes")
public class CampaignNoteController {

    private final CampaignNoteService campaignNoteService;

    public CampaignNoteController(CampaignNoteService campaignNoteService) {
        this.campaignNoteService = campaignNoteService;
    }

    @PostMapping("/campaign/{campaignId}/user/{userId}")
    public CampaignNoteApiDto createCampaignNote(@PathVariable Long campaignId, @PathVariable Long userId, @RequestBody CampaignNoteApiDto campaignNoteApiDto) {
        return campaignNoteService.createCampaignNote(campaignId, userId, campaignNoteApiDto);
    }

    @GetMapping("/{id}")
    public CampaignNoteApiDto getCampaignNoteById(@PathVariable Long id) {
        return campaignNoteService.getCampaignNoteById(id);
    }

    @GetMapping("/campaign/{campaignId}/user/{userId}")
    public List<CampaignNoteApiDto> getCampaignNoteByCampaignIdAndUserId(@PathVariable Long campaignId, @PathVariable Long userId) {
        return campaignNoteService.getCampaignNotesByCampaignIdAndUserId(campaignId, userId);
    }

    @PatchMapping("/{id}")
    public CampaignNoteApiDto updateCampaignNote(@PathVariable Long id, @RequestBody CampaignNoteApiDto campaignNoteApiDto) {
        return campaignNoteService.updateCampaignNoteById(id, campaignNoteApiDto);
    }

    @DeleteMapping("/{id}")
    public void deleteCampaignNoteById(@PathVariable Long id) {
        campaignNoteService.deleteCampaignNoteById(id);
    }
}
