package fr.ttl.atlasdd.sqldto.character.ogl5;

import fr.ttl.atlasdd.sqldto.BaseSqlDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
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
@Table(name = "ogl5_weapons")
public class WeaponSqlDto extends BaseSqlDto {

    private String index;
    private String name;
    private String weapon_range;
    private String cost;
    private String damage_dice;
    private String damage_type;

    @Column(precision = 2, scale = 1)
    private BigDecimal weight;

    private String properties;

    @ManyToMany(mappedBy = "weapons")
    private List<CharacterSheetSqlDto> characterSheets;
}
