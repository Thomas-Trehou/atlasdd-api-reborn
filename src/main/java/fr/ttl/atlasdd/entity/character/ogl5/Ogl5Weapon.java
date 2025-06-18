package fr.ttl.atlasdd.entity.character.ogl5;

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
@Table(name = "ogl5_weapons")
public class Ogl5Weapon extends BaseEntity {

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
    private List<Ogl5CharacterSheet> characterSheets;
}
