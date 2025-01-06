package fr.ttl.atlasdd.sqldto;

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
@Table(name = "classes")
public class ClassSqlDto extends BaseSqlDto {

    private String name;
    private String hitDice;
    private int startingHitPoints;
    private String startingEquipment;

    @OneToMany(mappedBy = "classe")
    private List<CharacterSheetSqlDto> characterSheets;

    @ManyToMany
    @JoinTable(
            name = "class_has_spells",
            joinColumns = @JoinColumn(name = "class_id"),
            inverseJoinColumns = @JoinColumn(name = "spell_id")
    )
    private List<SpellSqlDto> classSpells;
}
