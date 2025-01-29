package fr.ttl.atlasdd.service.campaign.impl;

import fr.ttl.atlasdd.apidto.campaign.CampaignApiDto;
import fr.ttl.atlasdd.apidto.campaign.CampaignCreateRequestApiDto;
import fr.ttl.atlasdd.mapper.campaign.CampaignMapper;
import fr.ttl.atlasdd.repository.UserRepo;
import fr.ttl.atlasdd.repository.campaign.CampaignRepo;
import fr.ttl.atlasdd.repository.custom.CustomCharacterSheetRepo;
import fr.ttl.atlasdd.repository.ogl5.CharacterSheetRepo;
import fr.ttl.atlasdd.service.campaign.CampaignService;
import fr.ttl.atlasdd.sqldto.UserSqlDto;
import fr.ttl.atlasdd.sqldto.campaign.CampaignSqlDto;
import fr.ttl.atlasdd.sqldto.custom.CustomCharacterSheetSqlDto;
import fr.ttl.atlasdd.sqldto.ogl5.CharacterSheetSqlDto;
import org.springframework.stereotype.Service;

@Service
public class CampaignServiceImpl implements CampaignService {

    private final CampaignRepo campaignRepository;
    private final UserRepo userRepository;
    private final CharacterSheetRepo ogl5CharacterSheetRepository;
    private final CustomCharacterSheetRepo customCharacterSheetRepo;
    private final CampaignMapper campaignMapper;

    public CampaignServiceImpl(
            CampaignRepo campaignRepository,
            UserRepo userRepository,
            CharacterSheetRepo ogl5CharacterSheetRepository,
            CustomCharacterSheetRepo customCharacterSheetRepo,
            CampaignMapper campaignMapper) {
        this.campaignRepository = campaignRepository;
        this.userRepository = userRepository;
        this.ogl5CharacterSheetRepository = ogl5CharacterSheetRepository;
        this.customCharacterSheetRepo = customCharacterSheetRepo;
        this.campaignMapper = campaignMapper;
    }

    @Override
    public CampaignApiDto createCampaign(CampaignCreateRequestApiDto campaignCreateRequestApiDto) {
        CampaignSqlDto newCampaign = new CampaignSqlDto();

        newCampaign.setName(campaignCreateRequestApiDto.getName());
        newCampaign.setDescription(campaignCreateRequestApiDto.getDescription());
        newCampaign.setGameMaster(userRepository.findById(campaignCreateRequestApiDto.getGameMasterId()).orElseThrow());

        return campaignMapper.toApiDto(campaignRepository.save(newCampaign));
    }

    @Override
    public CampaignApiDto getCampaignById(Long id) {
        return campaignMapper.toApiDto(campaignRepository.findById(id).orElseThrow());
    }

    @Override
    public CampaignApiDto updateCampaign(Long id, CampaignCreateRequestApiDto campaignCreateRequestApiDto) {
        CampaignSqlDto campaign = campaignRepository.findById(id).orElseThrow();

        campaign.setName(campaignCreateRequestApiDto.getName());
        campaign.setDescription(campaignCreateRequestApiDto.getDescription());
        campaign.setGameMaster(userRepository.findById(campaignCreateRequestApiDto.getGameMasterId()).orElseThrow());

        return campaignMapper.toApiDto(campaignRepository.save(campaign));
    }

    @Override
    public CampaignApiDto addPlayerToCampaign(Long campaignId, Long playerId) {
        CampaignSqlDto campaign = campaignRepository.findById(campaignId).orElseThrow();
        UserSqlDto player = userRepository.findById(playerId).orElseThrow();

        campaign.getCampaignPlayers().add(player);

        return campaignMapper.toApiDto(campaignRepository.save(campaign));
    }

    @Override
    public CampaignApiDto removePlayerFromCampaign(Long campaignId, Long playerId) {
        CampaignSqlDto campaign = campaignRepository.findById(campaignId).orElseThrow();
        UserSqlDto player = userRepository.findById(playerId).orElseThrow();

        campaign.getCampaignPlayers().remove(player);

        return campaignMapper.toApiDto(campaignRepository.save(campaign));
    }

    @Override
    public CampaignApiDto addOgl5CharacterToCampaign(Long campaignId, Long characterId) {
        CampaignSqlDto campaign = campaignRepository.findById(campaignId).orElseThrow();
        CharacterSheetSqlDto character = ogl5CharacterSheetRepository.findById(characterId).orElseThrow();

        campaign.getCampaignOgl5CharacterSheets().add(character);

        return campaignMapper.toApiDto(campaignRepository.save(campaign));
    }

    @Override
    public CampaignApiDto removeOgl5CharacterFromCampaign(Long campaignId, Long characterId) {
        CampaignSqlDto campaign = campaignRepository.findById(campaignId).orElseThrow();
        CharacterSheetSqlDto character = ogl5CharacterSheetRepository.findById(characterId).orElseThrow();

        campaign.getCampaignOgl5CharacterSheets().remove(character);

        return campaignMapper.toApiDto(campaignRepository.save(campaign));
    }

    @Override
    public CampaignApiDto addCustomCharacterToCampaign(Long campaignId, Long characterId) {
        CampaignSqlDto campaign = campaignRepository.findById(campaignId).orElseThrow();
        CustomCharacterSheetSqlDto character = customCharacterSheetRepo.findById(characterId).orElseThrow();

        campaign.getCampaignCustomCharacterSheets().add(character);

        return campaignMapper.toApiDto(campaignRepository.save(campaign));
    }

    @Override
    public CampaignApiDto removeCustomCharacterFromCampaign(Long campaignId, Long characterId) {
        CampaignSqlDto campaign = campaignRepository.findById(campaignId).orElseThrow();
        CustomCharacterSheetSqlDto character = customCharacterSheetRepo.findById(characterId).orElseThrow();

        campaign.getCampaignCustomCharacterSheets().remove(character);

        return campaignMapper.toApiDto(campaignRepository.save(campaign));
    }

    @Override
    public void deleteCampaign(Long id) {
        campaignRepository.deleteById(id);
    }
}
