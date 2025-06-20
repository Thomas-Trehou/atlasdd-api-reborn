package fr.ttl.atlasdd.entity.character.custom;

import fr.ttl.atlasdd.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "custom_classes")
public class CustomClass extends BaseEntity {

    private String spellcasterType;
    private String spellcastingAbility;
    private String name;
    private String hitDice;
    private int startingHitPoints;

    @Column(columnDefinition = "TEXT")
    private String startingEquipment;

    @OneToMany(mappedBy = "classe")
    private List<CustomCharacterSheet> characterSheets;
}
