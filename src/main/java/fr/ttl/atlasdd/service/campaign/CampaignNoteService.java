package fr.ttl.atlasdd.service.campaign;

import fr.ttl.atlasdd.apidto.campaign.CampaignNoteApiDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CampaignNoteService {

    CampaignNoteApiDto createCampaignNote(Long campaignId, Long userId, CampaignNoteApiDto campaignNoteApiDto);

    CampaignNoteApiDto getCampaignNoteById(Long id);

    List<CampaignNoteApiDto> getCampaignNotesByCampaignIdAndUserId(Long campaignId, Long userId);

    CampaignNoteApiDto updateCampaignNoteById(Long id, CampaignNoteApiDto campaignNoteApiDto);

    void deleteCampaignNotesByUserId(Long userId);

    void deleteCampaignNoteById(Long id);

}
