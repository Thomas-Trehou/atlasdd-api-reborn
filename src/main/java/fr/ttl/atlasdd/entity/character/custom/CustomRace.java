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