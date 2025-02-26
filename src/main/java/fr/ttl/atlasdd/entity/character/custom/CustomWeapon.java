package fr.ttl.atlasdd.entity.character.custom;

import fr.ttl.atlasdd.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "custom_weapons")
public class CustomWeapon extends BaseEntity {

    private String index;
    private String name;
    private String weaponRange;
    private String cost;
    private String damageDice;
    private String damageType;

    @Column(precision = 2, scale = 1)
    private BigDecimal weight;

    private String properties;

    @ManyToMany(mappedBy = "weapons")
    private List<CustomCharacterSheet> characterSheets;
}
