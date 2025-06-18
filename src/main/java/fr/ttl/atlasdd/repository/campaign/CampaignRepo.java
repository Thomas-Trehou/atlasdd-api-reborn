package fr.ttl.atlasdd.repository.campaign;

import fr.ttl.atlasdd.entity.campaign.Campaign;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CampaignRepo extends JpaRepository<Campaign, Long> {
    List<Campaign> findAllByCampaignPlayersId(Long playerId);
    List<Campaign> findAllByGameMasterId(Long dungeonMasterId);

    @Query("SELECT DISTINCT c FROM Campaign c LEFT JOIN c.campaignPlayers p " +
            "WHERE c.gameMaster.id = :userId OR p.id = :userId")
    List<Campaign> findAllByGameMasterIdOrPlayerId(@Param("userId") Long userId);

}
