package fr.ttl.atlasdd.sqldto.campaign;

import fr.ttl.atlasdd.sqldto.BaseSqlDto;
import fr.ttl.atlasdd.sqldto.UserSqlDto;
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
public class CampaignNoteSqlDto extends BaseSqlDto {

    private String title;

    @Column(columnDefinition = "TEXT")
    private String content;

    @ManyToOne
    @JoinColumn(name = "campaign_id")
    private CampaignSqlDto campaign;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private UserSqlDto owner;
}
