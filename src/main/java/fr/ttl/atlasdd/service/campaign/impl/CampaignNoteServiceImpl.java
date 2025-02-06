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
import fr.ttl.atlasdd.sqldto.user.UserSqlDto;
import fr.ttl.atlasdd.sqldto.campaign.CampaignNoteSqlDto;
import fr.ttl.atlasdd.sqldto.campaign.CampaignSqlDto;
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
        CampaignSqlDto campaignSqlDto =  campaignRepository.findById(campaignId)
                .orElseThrow(() -> new CampaignNotFoundException("Campagne non trouvée"));

        UserSqlDto owner = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("Utilisateur non trouvé"));

        CampaignNoteSqlDto newNote = new CampaignNoteSqlDto();
        campaignNoteMapper.updateSqlDto(campaignNoteApiDto, newNote);
        newNote.setCampaign(campaignSqlDto);
        newNote.setOwner(owner);

        try {
            return campaignNoteMapper.toApiDto(campaignNoteRepository.save(newNote));
        } catch (Exception e) {
            throw new CampaignNoteSavingErrorException("Erreur lors de la sauvegarde de la note de campagne");
        }
    }

    @Override
    public CampaignNoteApiDto getCampaignNoteById(Long id) {
        return campaignNoteMapper.toApiDto(campaignNoteRepository.findById(id)
                .orElseThrow(() -> new CampaignNoteNotFoundException("Note de campagne non trouvée")));
    }

    @Override
    public List<CampaignNoteApiDto> getCampaignNotesByCampaignIdAndUserId(Long campaignId, Long userId) {
       return campaignNoteRepository.findAllByCampaignIdAndOwnerId(campaignId, userId).stream().map(campaignNoteMapper::toApiDto).toList();
    }

    @Override
    public CampaignNoteApiDto updateCampaignNoteById(Long id, CampaignNoteApiDto campaignNoteApiDto) {
        CampaignNoteSqlDto note = campaignNoteRepository.findById(id)
                .orElseThrow(() -> new CampaignNoteNotFoundException("Note de campagne non trouvée"));

        note.setTitle(campaignNoteApiDto.getTitle());
        note.setContent(campaignNoteApiDto.getContent());

        try {
            return campaignNoteMapper.toApiDto(campaignNoteRepository.save(note));
        } catch (Exception e) {
            throw new CampaignNoteSavingErrorException("Erreur lors de la sauvegarde de la note de campagne");
        }
    }

    @Override
    public void deleteCampaignNoteById(Long id) {
        try {
            campaignNoteRepository.deleteById(id);
        } catch (Exception e) {
            throw new CampaignNoteSavingErrorException("Erreur lors de la suppression de la note de campagne");
        }
    }
}
