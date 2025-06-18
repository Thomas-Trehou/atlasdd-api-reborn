package fr.ttl.atlasdd.entity.character.ogl5;

import fr.ttl.atlasdd.entity.BaseEntity;
import fr.ttl.atlasdd.utils.character.ArmorCategory;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ogl5_armors")
public class Ogl5Armor extends BaseEntity {

    private String index;
    private String name;

    @Enumerated(EnumType.STRING)
    private ArmorCategory armorCategory;

    private int armorClass;
    private int strengthMinimum;
    private boolean stealthDisadvantage;

    @Column(precision = 3, scale = 1)
    private BigDecimal weight;

    private String cost;

    @Column(columnDefinition = "TEXT")
    private String properties;

    @OneToMany(mappedBy = "armor")
    private List<Ogl5CharacterSheet> characterSheet;
}
