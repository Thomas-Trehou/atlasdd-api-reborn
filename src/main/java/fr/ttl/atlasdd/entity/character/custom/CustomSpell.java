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
@Table(name = "custom_spells")
public class CustomSpell extends BaseEntity {

    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    private String range;
    private String components;

    @Column(columnDefinition = "TEXT")
    private String material;

    private String ritual;
    private String duration;
    private String concentration;
    private String castingTime;
    private String level;
    private String school;
    private String classes;

    @Column(columnDefinition = "TEXT")
    private String higherLevel;

    private String archetype;
    private String domains;
    private String oaths;
    private String circles;
    private String patrons;

    @ManyToMany(mappedBy = "preparedSpells")
    private List<CustomCharacterSheet> characterSheets;
}
