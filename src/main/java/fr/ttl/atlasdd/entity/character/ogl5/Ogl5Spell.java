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
@Table(name = "ogl5_spells")
public class Ogl5Spell extends BaseEntity {

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

    @ManyToMany(mappedBy = "raceSpells")
    private List<Ogl5Race> spellRaces;

    @ManyToMany(mappedBy = "classSpells")
    private List<Ogl5Class> spellClasses;

    @ManyToMany(mappedBy = "preparedSpells")
    private List<Ogl5CharacterSheet> characterSheets;
}
