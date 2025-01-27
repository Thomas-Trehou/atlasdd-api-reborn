package fr.ttl.atlasdd.sqldto.ogl5;

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
@Table(name = "ogl5_races")
public class RaceSqlDto extends BaseSqlDto {

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
    private List<CharacterSheetSqlDto> characterSheets;

    @ManyToMany
    @JoinTable(
            name = "ogl5_race_has_spells",
            joinColumns = @JoinColumn(name = "race_id"),
            inverseJoinColumns = @JoinColumn(name = "spell_id")
    )
    private List<SpellSqlDto> raceSpells;
}