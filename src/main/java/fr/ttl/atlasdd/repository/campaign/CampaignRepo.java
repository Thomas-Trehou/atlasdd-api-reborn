package fr.ttl.atlasdd.repository.campaign;

import fr.ttl.atlasdd.sqldto.campaign.CampaignSqlDto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CampaignRepo extends JpaRepository<CampaignSqlDto, Long> {
}
