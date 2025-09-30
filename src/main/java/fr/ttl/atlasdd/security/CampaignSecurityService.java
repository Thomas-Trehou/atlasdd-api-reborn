package fr.ttl.atlasdd.security;

import fr.ttl.atlasdd.entity.campaign.Campaign;
import fr.ttl.atlasdd.repository.campaign.CampaignRepo;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component("campaignSecurityService")
public class CampaignSecurityService {

    private final CampaignRepo campaignRepository;

    public CampaignSecurityService(CampaignRepo campaignRepository) {
        this.campaignRepository = campaignRepository;
    }

    /**
     * Vérifie si l'utilisateur authentifié est le Maître du Jeu (GM) de la campagne.
     */
    public boolean isGameMaster(Authentication authentication, Long campaignId) {
        String userEmail = authentication.getName();
        Optional<Campaign> campaignOpt = campaignRepository.findById(campaignId);

        return campaignOpt.map(campaign -> campaign.getGameMaster().getEmail().equals(userEmail))
                .orElse(false);
    }

    /**
     * Vérifie si l'utilisateur authentifié est un membre de la campagne (GM ou joueur).
     */
    public boolean isMember(Authentication authentication, Long campaignId) {
        String userEmail = authentication.getName();
        Optional<Campaign> campaignOpt = campaignRepository.findById(campaignId);

        if (campaignOpt.isEmpty()) {
            return false;
        }

        Campaign campaign = campaignOpt.get();
        // L'utilisateur est-il le GM ?
        if (campaign.getGameMaster().getEmail().equals(userEmail)) {
            return true;
        }
        // L'utilisateur est-il dans la liste des joueurs ?
        return campaign.getCampaignPlayers().stream()
                .anyMatch(player -> player.getEmail().equals(userEmail));
    }
}