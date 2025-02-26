package fr.ttl.atlasdd.repository.campaign;

import fr.ttl.atlasdd.entity.campaign.Campaign;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CampaignRepo extends JpaRepository<Campaign, Long> {
    List<Campaign> findAllByCampaignPlayersId(Long playerId);
    List<Campaign> findAllByGameMasterId(Long dungeonMasterId);
}
