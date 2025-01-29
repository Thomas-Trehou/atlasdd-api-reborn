package fr.ttl.atlasdd.repository.campaign;

import fr.ttl.atlasdd.sqldto.campaign.CampaignNoteSqlDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CampaignNoteRepo extends JpaRepository<CampaignNoteSqlDto, Long> {

    List<CampaignNoteSqlDto> findAllByCampaignIdAndOwnerId(Long campaignId,Long userId);
}
