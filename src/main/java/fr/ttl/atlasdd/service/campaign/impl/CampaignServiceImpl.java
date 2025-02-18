package fr.ttl.atlasdd.service.campaign.impl;

import fr.ttl.atlasdd.apidto.campaign.CampaignApiDto;
import fr.ttl.atlasdd.apidto.campaign.CampaignCreateRequestApiDto;
import fr.ttl.atlasdd.exception.campaign.CampaignNotFoundException;
import fr.ttl.atlasdd.exception.campaign.CampaignSavingErrorException;
import fr.ttl.atlasdd.exception.character.custom.notfound.CustomCharacterNotFoundException;
import fr.ttl.atlasdd.exception.character.ogl5.notfound.Ogl5CharacterNotFoundException;
import fr.ttl.atlasdd.exception.user.UserNotFoundException;
import fr.ttl.atlasdd.mapper.campaign.CampaignMapper;
import fr.ttl.atlasdd.repository.user.UserRepo;
import fr.ttl.atlasdd.repository.campaign.CampaignRepo;
import fr.ttl.atlasdd.repository.character.custom.CustomCharacterSheetRepo;
import fr.ttl.atlasdd.repository.character.ogl5.CharacterSheetRepo;
import fr.ttl.atlasdd.service.campaign.CampaignService;
import fr.ttl.atlasdd.sqldto.user.UserSqlDto;
import fr.ttl.atlasdd.sqldto.campaign.CampaignSqlDto;
import fr.ttl.atlasdd.sqldto.character.custom.CustomCharacterSheetSqlDto;
import fr.ttl.atlasdd.sqldto.character.ogl5.CharacterSheetSqlDto;
import fr.ttl.atlasdd.utils.exception.ExceptionMessage;
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
        UserSqlDto gameMaster = userRepository.findById(campaignCreateRequestApiDto.getGameMasterId())
                .orElseThrow(() -> new UserNotFoundException(ExceptionMessage.USER_NOT_FOUND.getMessage()));

        newCampaign.setName(campaignCreateRequestApiDto.getName());
        newCampaign.setDescription(campaignCreateRequestApiDto.getDescription());
        newCampaign.setGameMaster(gameMaster);

        try {
            return campaignMapper.toApiDto(campaignRepository.save(newCampaign));
        } catch (Exception e) {
            throw new CampaignSavingErrorException(ExceptionMessage.CAMPAIGN_SAVE_ERROR.getMessage());
        }
    }

    @Override
    public CampaignApiDto getCampaignById(Long id) {
        return campaignMapper.toApiDto(
                campaignRepository.findById(id)
                        .orElseThrow(() -> new CampaignNotFoundException(ExceptionMessage.CAMPAIGN_NOT_FOUND.getMessage())));
    }

    @Override
    public CampaignApiDto updateCampaign(Long id, CampaignCreateRequestApiDto campaignCreateRequestApiDto) {
        CampaignSqlDto campaign = campaignRepository.findById(id)
                .orElseThrow(() -> new CampaignNotFoundException(ExceptionMessage.CAMPAIGN_NOT_FOUND.getMessage()));

        campaign.setName(campaignCreateRequestApiDto.getName());
        campaign.setDescription(campaignCreateRequestApiDto.getDescription());
        campaign.setGameMaster(
                userRepository.findById(campaignCreateRequestApiDto.getGameMasterId())
                        .orElseThrow(() -> new UserNotFoundException(ExceptionMessage.USER_NOT_FOUND.getMessage()))
        );

        try {
            return campaignMapper.toApiDto(campaignRepository.save(campaign));
        } catch (Exception e) {
            throw new CampaignSavingErrorException(ExceptionMessage.CAMPAIGN_UPDATE_ERROR.getMessage());
        }
    }

    @Override
    public CampaignApiDto addPlayerToCampaign(Long campaignId, Long playerId) {
        CampaignSqlDto campaign = campaignRepository.findById(campaignId)
                .orElseThrow(() -> new CampaignNotFoundException(ExceptionMessage.CAMPAIGN_NOT_FOUND.getMessage()));

        UserSqlDto player = userRepository.findById(playerId)
                .orElseThrow(() -> new UserNotFoundException(ExceptionMessage.USER_NOT_FOUND.getMessage()));

        if (campaign.getCampaignPlayers().contains(player)) {
            return campaignMapper.toApiDto(campaign);
        }

        campaign.getCampaignPlayers().add(player);

        try {
            return campaignMapper.toApiDto(campaignRepository.save(campaign));
        } catch (Exception e) {
            throw new CampaignSavingErrorException(ExceptionMessage.ADD_PLAYER_TO_CAMPAIGN_ERROR.getMessage());
        }
    }

    @Override
    public CampaignApiDto removePlayerFromCampaign(Long campaignId, Long playerId) {
        CampaignSqlDto campaign = campaignRepository.findById(campaignId)
                .orElseThrow(() -> new CampaignNotFoundException(ExceptionMessage.CAMPAIGN_NOT_FOUND.getMessage()));

        UserSqlDto player = userRepository.findById(playerId)
                .orElseThrow(() -> new UserNotFoundException(ExceptionMessage.USER_NOT_FOUND.getMessage()));

        campaign.getCampaignPlayers().remove(player);

        try {
            return campaignMapper.toApiDto(campaignRepository.save(campaign));
        } catch (Exception e) {
            throw new CampaignSavingErrorException(ExceptionMessage.REMOVE_PLAYER_FROM_CAMPAIGN_ERROR.getMessage());
        }
    }

    @Override
    public CampaignApiDto addOgl5CharacterToCampaign(Long campaignId, Long characterId) {
        CampaignSqlDto campaign = campaignRepository.findById(campaignId)
                .orElseThrow(() -> new CampaignNotFoundException(ExceptionMessage.CAMPAIGN_NOT_FOUND.getMessage()));

        CharacterSheetSqlDto character = ogl5CharacterSheetRepository.findById(characterId)
                .orElseThrow(() -> new Ogl5CharacterNotFoundException(ExceptionMessage.CHARACTER_NOT_FOUND.getMessage()));

        if (campaign.getCampaignOgl5CharacterSheets().contains(character)) {
            return campaignMapper.toApiDto(campaign);
        }
        campaign.getCampaignOgl5CharacterSheets().add(character);

        try {
            return campaignMapper.toApiDto(campaignRepository.save(campaign));
        } catch (Exception e) {
            throw new CampaignSavingErrorException(ExceptionMessage.ADD_CHARACTER_TO_CAMPAIGN_ERROR.getMessage());
        }
    }

    @Override
    public CampaignApiDto removeOgl5CharacterFromCampaign(Long campaignId, Long characterId) {
        CampaignSqlDto campaign = campaignRepository.findById(campaignId)
                .orElseThrow(() -> new CampaignNotFoundException(ExceptionMessage.CAMPAIGN_NOT_FOUND.getMessage()));

        CharacterSheetSqlDto character = ogl5CharacterSheetRepository.findById(characterId)
                .orElseThrow(() -> new Ogl5CharacterNotFoundException(ExceptionMessage.CHARACTER_NOT_FOUND.getMessage()));

        if (!campaign.getCampaignOgl5CharacterSheets().contains(character)) {
            return campaignMapper.toApiDto(campaign);
        }

        campaign.getCampaignOgl5CharacterSheets().remove(character);

        try {
            return campaignMapper.toApiDto(campaignRepository.save(campaign));
        } catch (Exception e) {
            throw new CampaignSavingErrorException(ExceptionMessage.REMOVE_CHARACTER_FROM_CAMPAIGN_ERROR.getMessage());
        }
    }

    @Override
    public CampaignApiDto addCustomCharacterToCampaign(Long campaignId, Long characterId) {
        CampaignSqlDto campaign = campaignRepository.findById(campaignId)
                .orElseThrow(() -> new CampaignNotFoundException(ExceptionMessage.CAMPAIGN_NOT_FOUND.getMessage()));

        CustomCharacterSheetSqlDto character = customCharacterSheetRepo.findById(characterId)
                .orElseThrow(() -> new CustomCharacterNotFoundException(ExceptionMessage.CHARACTER_NOT_FOUND.getMessage()));

        if (campaign.getCampaignCustomCharacterSheets().contains(character)) {
            return campaignMapper.toApiDto(campaign);
        }

        campaign.getCampaignCustomCharacterSheets().add(character);

        try {
            return campaignMapper.toApiDto(campaignRepository.save(campaign));
        } catch (Exception e) {
            throw new CampaignSavingErrorException(ExceptionMessage.ADD_CHARACTER_TO_CAMPAIGN_ERROR.getMessage());
        }
    }

    @Override
    public CampaignApiDto removeCustomCharacterFromCampaign(Long campaignId, Long characterId) {
        CampaignSqlDto campaign = campaignRepository.findById(campaignId)
                .orElseThrow(() -> new CampaignNotFoundException(ExceptionMessage.CAMPAIGN_NOT_FOUND.getMessage()));

        CustomCharacterSheetSqlDto character = customCharacterSheetRepo.findById(characterId)
                .orElseThrow(() -> new CustomCharacterNotFoundException(ExceptionMessage.CHARACTER_NOT_FOUND.getMessage()));

        if (!campaign.getCampaignCustomCharacterSheets().contains(character)) {
            return campaignMapper.toApiDto(campaign);
        }

        campaign.getCampaignCustomCharacterSheets().remove(character);

        try {
            return campaignMapper.toApiDto(campaignRepository.save(campaign));
        } catch (Exception e) {
            throw new CampaignSavingErrorException(ExceptionMessage.REMOVE_CHARACTER_FROM_CAMPAIGN_ERROR.getMessage());
        }
    }

    @Override
    public void deleteCampaign(Long id) {
        try {
            campaignRepository.deleteById(id);
        } catch (Exception e) {
            throw new CampaignSavingErrorException(ExceptionMessage.CAMPAIGN_DELETE_ERROR.getMessage());
        }
    }
}
