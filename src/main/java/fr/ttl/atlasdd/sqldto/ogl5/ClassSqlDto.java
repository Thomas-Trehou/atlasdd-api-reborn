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
@Table(name = "ogl5_classes")
public class ClassSqlDto extends BaseSqlDto {

    private String name;
    private String hitDice;
    private int startingHitPoints;

    @Column(columnDefinition = "TEXT")
    private String startingEquipment;

    @OneToMany(mappedBy = "classe")
    private List<CharacterSheetSqlDto> characterSheets;

    @ManyToMany
    @JoinTable(
            name = "ogl5_class_has_spells",
            joinColumns = @JoinColumn(name = "class_id"),
            inverseJoinColumns = @JoinColumn(name = "spell_id")
    )
    private List<SpellSqlDto> classSpells;
}
