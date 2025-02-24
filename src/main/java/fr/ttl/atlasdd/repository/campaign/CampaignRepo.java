package fr.ttl.atlasdd.repository.campaign;

import fr.ttl.atlasdd.sqldto.campaign.CampaignSqlDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CampaignRepo extends JpaRepository<CampaignSqlDto, Long> {
    List<CampaignSqlDto> findAllByCampaignPlayersId(Long playerId);
    List<CampaignSqlDto> findAllByGameMasterId(Long dungeonMasterId);
}
