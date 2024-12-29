package fr.ttl.atlasdd.sqldto;

import fr.ttl.atlasdd.listeners.SpellEntityListener;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.ManyToMany;
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
@Table(name = "spells")
@EntityListeners(SpellEntityListener.class)
public class SpellSqlDto extends BaseSqlDto{

    private String name;
    private String description;
    private String range;
    private String components;
    private String material;
    private String ritual;
    private String duration;
    private String concentration;
    private String castingTime;
    private String level;
    private String school;
    private String classes;
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
