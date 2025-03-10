package fr.ttl.atlasdd.apidto.character;

import fr.ttl.atlasdd.apidto.BaseApiDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class WeaponApiDto extends BaseApiDto {

    private String index;
    private String name;
    private String weaponRange;
    private String cost;
    private String damageDice;
    private String damageType;
    private BigDecimal weight;
    private String properties;
}
