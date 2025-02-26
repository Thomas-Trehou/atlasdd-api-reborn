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
@Table(name = "ogl5_classes")
public class Ogl5Class extends BaseEntity {

    private String name;
    private String hitDice;
    private int startingHitPoints;

    @Column(columnDefinition = "TEXT")
    private String startingEquipment;

    @OneToMany(mappedBy = "classe")
    private List<Ogl5CharacterSheet> characterSheets;

    @ManyToMany
    @JoinTable(
            name = "ogl5_class_has_spells",
            joinColumns = @JoinColumn(name = "class_id"),
            inverseJoinColumns = @JoinColumn(name = "spell_id")
    )
    private List<Ogl5Spell> classSpells;
}
