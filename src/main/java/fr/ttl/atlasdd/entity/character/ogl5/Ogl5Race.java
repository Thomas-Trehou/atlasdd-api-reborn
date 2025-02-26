package fr.ttl.atlasdd.entity.character.ogl5;

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
@Table(name = "ogl5_races")
public class Ogl5Race extends BaseEntity {

    private String name;
    private String speed;
    private int strengthBonus;
    private int dexterityBonus;
    private int constitutionBonus;
    private int intelligenceBonus;
    private int wisdomBonus;
    private int charismaBonus;
    private String languages;

    @Column(columnDefinition = "TEXT")
    private String traits;

    @OneToMany(mappedBy = "race")
    private List<Ogl5CharacterSheet> characterSheets;

    @ManyToMany
    @JoinTable(
            name = "ogl5_race_has_spells",
            joinColumns = @JoinColumn(name = "race_id"),
            inverseJoinColumns = @JoinColumn(name = "spell_id")
    )
    private List<Ogl5Spell> raceSpells;
}