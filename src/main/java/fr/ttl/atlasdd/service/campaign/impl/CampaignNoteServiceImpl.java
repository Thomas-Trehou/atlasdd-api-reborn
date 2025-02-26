package fr.ttl.atlasdd.service.campaign.impl;

import fr.ttl.atlasdd.apidto.campaign.CampaignNoteApiDto;
import fr.ttl.atlasdd.exception.campaign.CampaignNotFoundException;
import fr.ttl.atlasdd.exception.campaign.CampaignNoteNotFoundException;
import fr.ttl.atlasdd.exception.campaign.CampaignNoteSavingErrorException;
import fr.ttl.atlasdd.exception.user.UserNotFoundException;
import fr.ttl.atlasdd.mapper.campaign.CampaignNoteMapper;
import fr.ttl.atlasdd.repository.user.UserRepo;
import fr.ttl.atlasdd.repository.campaign.CampaignNoteRepo;
import fr.ttl.atlasdd.repository.campaign.CampaignRepo;
import fr.ttl.atlasdd.service.campaign.CampaignNoteService;
import fr.ttl.atlasdd.entity.campaign.Campaign;
import fr.ttl.atlasdd.entity.user.User;
import fr.ttl.atlasdd.entity.campaign.CampaignNote;
import fr.ttl.atlasdd.utils.exception.ExceptionMessage;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CampaignNoteServiceImpl implements CampaignNoteService {

    private final CampaignNoteRepo campaignNoteRepository;
    private final CampaignRepo campaignRepository;
    private final UserRepo userRepository;
    private final CampaignNoteMapper campaignNoteMapper;

    public CampaignNoteServiceImpl(
            CampaignNoteRepo campaignNoteRepository,
            CampaignRepo campaignRepository,
            UserRepo userRepository,
            CampaignNoteMapper campaignNoteMapper
    ) {
        this.campaignNoteRepository = campaignNoteRepository;
        this.campaignRepository = campaignRepository;
        this.userRepository = userRepository;
        this.campaignNoteMapper = campaignNoteMapper;
    }

    @Override
    public CampaignNoteApiDto createCampaignNote(Long campaignId, Long userId, CampaignNoteApiDto campaignNoteApiDto) {
        Campaign campaign =  campaignRepository.findById(campaignId)
                .orElseThrow(() -> new CampaignNotFoundException(ExceptionMessage.CAMPAIGN_NOT_FOUND.getMessage()));

        User owner = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(ExceptionMessage.USER_NOT_FOUND.getMessage()));

        CampaignNote newNote = new CampaignNote();
        campaignNoteMapper.updateSqlDto(campaignNoteApiDto, newNote);
        newNote.setCampaign(campaign);
        newNote.setOwner(owner);

        try {
            return campaignNoteMapper.toApiDto(campaignNoteRepository.save(newNote));
        } catch (Exception e) {
            throw new CampaignNoteSavingErrorException(ExceptionMessage.CAMPAIGN_NOTE_SAVE_ERROR.getMessage());
        }
    }

    @Override
    public CampaignNoteApiDto getCampaignNoteById(Long id) {
        return campaignNoteMapper.toApiDto(campaignNoteRepository.findById(id)
                .orElseThrow(() -> new CampaignNoteNotFoundException(ExceptionMessage.CAMPAIGN_NOTE_NOT_FOUND.getMessage())));
    }

    @Override
    public List<CampaignNoteApiDto> getCampaignNotesByCampaignIdAndUserId(Long campaignId, Long userId) {

        if (!campaignRepository.existsById(campaignId)) {
            throw new CampaignNotFoundException(ExceptionMessage.CAMPAIGN_NOT_FOUND.getMessage());
        }

        if (!userRepository.existsById(userId)) {
            throw new UserNotFoundException(ExceptionMessage.USER_NOT_FOUND.getMessage());
        }

       return campaignNoteRepository.findAllByCampaignIdAndOwnerId(campaignId, userId).stream().map(campaignNoteMapper::toApiDto).toList();
    }

    @Override
    public CampaignNoteApiDto updateCampaignNoteById(Long id, CampaignNoteApiDto campaignNoteApiDto) {
        CampaignNote note = campaignNoteRepository.findById(id)
                .orElseThrow(() -> new CampaignNoteNotFoundException(ExceptionMessage.CAMPAIGN_NOTE_NOT_FOUND.getMessage()));

        note.setTitle(campaignNoteApiDto.getTitle());
        note.setContent(campaignNoteApiDto.getContent());

        try {
            return campaignNoteMapper.toApiDto(campaignNoteRepository.save(note));
        } catch (Exception e) {
            throw new CampaignNoteSavingErrorException(ExceptionMessage.CAMPAIGN_NOTE_UPDATE_ERROR.getMessage());
        }
    }

    @Override
    public void deleteCampaignNotesByUserId(Long id) {
        try {
            campaignNoteRepository.deleteAllByOwnerId(id);
        } catch (Exception e) {
            throw new CampaignNoteSavingErrorException(ExceptionMessage.CAMPAIGN_NOTE_DELETE_ERROR.getMessage());
        }
    }

    @Override
    public void deleteCampaignNoteById(Long id) {
        try {
            campaignNoteRepository.deleteById(id);
        } catch (Exception e) {
            throw new CampaignNoteSavingErrorException(ExceptionMessage.CAMPAIGN_NOTE_DELETE_ERROR.getMessage());
        }
    }
}
