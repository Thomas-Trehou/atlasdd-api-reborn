package fr.ttl.atlasdd.entity.character.ogl5;

import fr.ttl.atlasdd.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ogl5_backgrounds")
public class Ogl5Background extends BaseEntity {

    private String name;
    private String masteredTools;

    @Column(columnDefinition = "TEXT")
    private String startingEquipment;

    private String backgroundFeature;

    @OneToMany(mappedBy = "background")
    private List<Ogl5CharacterSheet> characterSheets;
}
