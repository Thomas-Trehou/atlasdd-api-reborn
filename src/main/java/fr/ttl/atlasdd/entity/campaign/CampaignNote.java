package fr.ttl.atlasdd.entity.campaign;

import fr.ttl.atlasdd.entity.BaseEntity;
import fr.ttl.atlasdd.entity.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table(name = "campaign_notes")
public class CampaignNote extends BaseEntity {

    private String title;

    @Column(columnDefinition = "TEXT")
    private String content;

    @ManyToOne
    @JoinColumn(name = "campaign_id")
    private Campaign campaign;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private User owner;
}
