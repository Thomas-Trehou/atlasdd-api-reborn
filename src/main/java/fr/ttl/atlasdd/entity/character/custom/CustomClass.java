package fr.ttl.atlasdd.entity.character.custom;

import fr.ttl.atlasdd.entity.BaseEntity;
import jakarta.persistence.*;
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
@Table(name = "custom_classes")
public class CustomClass extends BaseEntity {

    private String name;
    private String hitDice;
    private int startingHitPoints;

    @Column(columnDefinition = "TEXT")
    private String startingEquipment;

    @OneToMany(mappedBy = "classe")
    private List<CustomCharacterSheet> characterSheets;
}
