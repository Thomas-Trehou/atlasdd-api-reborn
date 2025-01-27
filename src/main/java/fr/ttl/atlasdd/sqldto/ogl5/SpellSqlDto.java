package fr.ttl.atlasdd.sqldto.ogl5;

import fr.ttl.atlasdd.listeners.SpellEntityListener;
import fr.ttl.atlasdd.sqldto.BaseSqlDto;
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
@Table(name = "ogl5_spells")
@EntityListeners(SpellEntityListener.class)
public class SpellSqlDto extends BaseSqlDto {

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
    private List<RaceSqlDto> spellRaces;

    @ManyToMany(mappedBy = "classSpells")
    private List<ClassSqlDto> spellClasses;

    @ManyToMany(mappedBy = "preparedSpells")
    private List<CharacterSheetSqlDto> characterSheets;
}
