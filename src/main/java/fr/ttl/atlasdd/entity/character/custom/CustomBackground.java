package fr.ttl.atlasdd.entity.character.custom;

import fr.ttl.atlasdd.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table(name = "custom_backgrounds")
public class CustomBackground extends BaseEntity {

    private String name;
    private String masteredTools;

    @Column(columnDefinition = "TEXT")
    private String startingEquipment;

    private String backgroundFeature;

    @OneToMany(mappedBy = "background")
    private List<CustomCharacterSheet> characterSheets;
}
