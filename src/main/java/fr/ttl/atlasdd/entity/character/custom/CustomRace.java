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
@Table(name = "custom_races")
public class CustomRace extends BaseEntity {

    private String name;
    private String speed;
    private String languages;

    @Column(columnDefinition = "TEXT")
    private String traits;

    @OneToMany(mappedBy = "race")
    private List<CustomCharacterSheet> characterSheets;
}