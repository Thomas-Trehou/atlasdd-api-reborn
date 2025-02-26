package fr.ttl.atlasdd.entity.character.custom;

import fr.ttl.atlasdd.entity.BaseEntity;
import fr.ttl.atlasdd.utils.character.ArmorCategory;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table(name = "custom_armors")
public class CustomArmor extends BaseEntity {

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
    private List<CustomCharacterSheet> characterSheet;
}
