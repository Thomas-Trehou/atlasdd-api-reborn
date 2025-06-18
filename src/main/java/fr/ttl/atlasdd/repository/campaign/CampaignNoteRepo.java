package fr.ttl.atlasdd.repository.campaign;

import fr.ttl.atlasdd.entity.campaign.CampaignNote;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CampaignNoteRepo extends JpaRepository<CampaignNote, Long> {

    List<CampaignNote> findAllByCampaignIdAndOwnerId(Long campaignId, Long userId);

    @Query("DELETE FROM CampaignNote e WHERE e.owner.id = :userId")
    @Modifying
    @Transactional
    void deleteAllByOwnerId(@Param("userId") Long userId);
}
