package fr.ttl.atlasdd.service.campaign.impl;

import fr.ttl.atlasdd.apidto.campaign.CampaignApiDto;
import fr.ttl.atlasdd.apidto.campaign.CampaignCreateRequestApiDto;
import fr.ttl.atlasdd.exception.campaign.CampaignNotFoundException;
import fr.ttl.atlasdd.exception.campaign.CampaignSavingErrorException;
import fr.ttl.atlasdd.exception.character.custom.notfound.CustomCharacterNotFoundException;
import fr.ttl.atlasdd.exception.character.ogl5.Ogl5CharacterNotFoundException;
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
                .orElseThrow(() -> new UserNotFoundException("Utilisateur non trouvé", 404));

        newCampaign.setName(campaignCreateRequestApiDto.getName());
        newCampaign.setDescription(campaignCreateRequestApiDto.getDescription());
        newCampaign.setGameMaster(gameMaster);

        try {
            return campaignMapper.toApiDto(campaignRepository.save(newCampaign));
        } catch (Exception e) {
            throw new CampaignSavingErrorException("Erreur lors de la sauvegarde de la campagne", 500);
        }
    }

    @Override
    public CampaignApiDto getCampaignById(Long id) {
        return campaignMapper.toApiDto(
                campaignRepository.findById(id)
                        .orElseThrow(() -> new CampaignNotFoundException("Campagne non trouvée", 404)));
    }

    @Override
    public CampaignApiDto updateCampaign(Long id, CampaignCreateRequestApiDto campaignCreateRequestApiDto) {
        CampaignSqlDto campaign = campaignRepository.findById(id)
                .orElseThrow(() -> new CampaignNotFoundException("Campagne non trouvée", 404));

        campaign.setName(campaignCreateRequestApiDto.getName());
        campaign.setDescription(campaignCreateRequestApiDto.getDescription());
        campaign.setGameMaster(userRepository.findById(campaignCreateRequestApiDto.getGameMasterId()).orElseThrow());

        try {
            return campaignMapper.toApiDto(campaignRepository.save(campaign));
        } catch (Exception e) {
            throw new CampaignSavingErrorException("Erreur lors de la sauvegarde de la campagne", 500);
        }
    }

    @Override
    public CampaignApiDto addPlayerToCampaign(Long campaignId, Long playerId) {
        CampaignSqlDto campaign = campaignRepository.findById(campaignId)
                .orElseThrow(() -> new CampaignNotFoundException("Campagne non trouvée", 404));

        UserSqlDto player = userRepository.findById(playerId)
                .orElseThrow(() -> new UserNotFoundException("Utilisateur non trouvé", 404));

        if (campaign.getCampaignPlayers().contains(player)) {
            return campaignMapper.toApiDto(campaign);
        }

        campaign.getCampaignPlayers().add(player);

        try {
            return campaignMapper.toApiDto(campaignRepository.save(campaign));
        } catch (Exception e) {
            throw new CampaignSavingErrorException("Erreur lors de la sauvegarde de la campagne", 500);
        }
    }

    @Override
    public CampaignApiDto removePlayerFromCampaign(Long campaignId, Long playerId) {
        CampaignSqlDto campaign = campaignRepository.findById(campaignId)
                .orElseThrow(() -> new CampaignNotFoundException("Campagne non trouvée", 404));

        UserSqlDto player = userRepository.findById(playerId)
                .orElseThrow(() -> new UserNotFoundException("Utilisateur non trouvé", 404));

        campaign.getCampaignPlayers().remove(player);

        try {
            return campaignMapper.toApiDto(campaignRepository.save(campaign));
        } catch (Exception e) {
            throw new CampaignSavingErrorException("Erreur lors de la sauvegarde de la campagne", 500);
        }
    }

    @Override
    public CampaignApiDto addOgl5CharacterToCampaign(Long campaignId, Long characterId) {
        CampaignSqlDto campaign = campaignRepository.findById(campaignId)
                .orElseThrow(() -> new CampaignNotFoundException("Campagne non trouvée", 404));

        CharacterSheetSqlDto character = ogl5CharacterSheetRepository.findById(characterId)
                .orElseThrow(() -> new Ogl5CharacterNotFoundException("Personnage non trouvé", 404));

        campaign.getCampaignOgl5CharacterSheets().add(character);

        try {
            return campaignMapper.toApiDto(campaignRepository.save(campaign));
        } catch (Exception e) {
            throw new CampaignSavingErrorException("Erreur lors de la sauvegarde de la campagne", 500);
        }
    }

    @Override
    public CampaignApiDto removeOgl5CharacterFromCampaign(Long campaignId, Long characterId) {
        CampaignSqlDto campaign = campaignRepository.findById(campaignId)
                .orElseThrow(() -> new CampaignNotFoundException("Campagne non trouvée", 404));

        CharacterSheetSqlDto character = ogl5CharacterSheetRepository.findById(characterId)
                .orElseThrow(() -> new Ogl5CharacterNotFoundException("Personnage non trouvé", 404));

        campaign.getCampaignOgl5CharacterSheets().remove(character);

        try {
            return campaignMapper.toApiDto(campaignRepository.save(campaign));
        } catch (Exception e) {
            throw new CampaignSavingErrorException("Erreur lors de la sauvegarde de la campagne", 500);
        }
    }

    @Override
    public CampaignApiDto addCustomCharacterToCampaign(Long campaignId, Long characterId) {
        CampaignSqlDto campaign = campaignRepository.findById(campaignId)
                .orElseThrow(() -> new CampaignNotFoundException("Campagne non trouvée", 404));

        CustomCharacterSheetSqlDto character = customCharacterSheetRepo.findById(characterId)
                .orElseThrow(() -> new CustomCharacterNotFoundException("Personnage non trouvé", 404));

        campaign.getCampaignCustomCharacterSheets().add(character);

        try {
            return campaignMapper.toApiDto(campaignRepository.save(campaign));
        } catch (Exception e) {
            throw new CampaignSavingErrorException("Erreur lors de la sauvegarde de la campagne", 500);
        }
    }

    @Override
    public CampaignApiDto removeCustomCharacterFromCampaign(Long campaignId, Long characterId) {
        CampaignSqlDto campaign = campaignRepository.findById(campaignId)
                .orElseThrow(() -> new CampaignNotFoundException("Campagne non trouvée", 404));

        CustomCharacterSheetSqlDto character = customCharacterSheetRepo.findById(characterId)
                .orElseThrow(() -> new CustomCharacterNotFoundException("Personnage non trouvé", 404));

        campaign.getCampaignCustomCharacterSheets().remove(character);

        try {
            return campaignMapper.toApiDto(campaignRepository.save(campaign));
        } catch (Exception e) {
            throw new CampaignSavingErrorException("Erreur lors de la sauvegarde de la campagne", 500);
        }
    }

    @Override
    public void deleteCampaign(Long id) {
        try {
            campaignRepository.deleteById(id);
        } catch (Exception e) {
            throw new CampaignSavingErrorException("Erreur lors de la suppression de la campagne", 500);
        }
    }
}
