package fr.ttl.atlasdd.service.campaign.impl;

import fr.ttl.atlasdd.apidto.campaign.CampaignNoteApiDto;
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
        CampaignSqlDto campaignSqlDto =  campaignRepository.findById(campaignId).orElseThrow(() -> new RuntimeException("Campaign not found"));
        UserSqlDto owner = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));

        CampaignNoteSqlDto newNote = new CampaignNoteSqlDto();
        campaignNoteMapper.updateSqlDto(campaignNoteApiDto, newNote);
        newNote.setCampaign(campaignSqlDto);
        newNote.setOwner(owner);

        return campaignNoteMapper.toApiDto(campaignNoteRepository.save(newNote));
    }

    @Override
    public CampaignNoteApiDto getCampaignNoteById(Long id) {
        return campaignNoteMapper.toApiDto(campaignNoteRepository.findById(id).orElseThrow(() -> new RuntimeException("Campaign note not found")));
    }

    @Override
    public List<CampaignNoteApiDto> getCampaignNotesByCampaignIdAndUserId(Long campaignId, Long userId) {
       return campaignNoteRepository.findAllByCampaignIdAndOwnerId(campaignId, userId).stream().map(campaignNoteMapper::toApiDto).toList();
    }

    @Override
    public CampaignNoteApiDto updateCampaignNoteById(Long id, CampaignNoteApiDto campaignNoteApiDto) {
        CampaignNoteSqlDto note = campaignNoteRepository.findById(id).orElseThrow(() -> new RuntimeException("Campaign note not found"));

        note.setTitle(campaignNoteApiDto.getTitle());
        note.setContent(campaignNoteApiDto.getContent());

        return campaignNoteMapper.toApiDto(campaignNoteRepository.save(note));
    }

    @Override
    public void deleteCampaignNoteById(Long id) {
        campaignNoteRepository.deleteById(id);
    }
}
