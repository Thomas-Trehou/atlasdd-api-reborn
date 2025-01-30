package fr.ttl.atlasdd.apidto.character.custom;

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
public class CustomWeaponApiDto extends BaseApiDto {

    private String index;
    private String name;
    private String weapon_range;
    private String cost;
    private String damage_dice;
    private String damage_type;
    private BigDecimal weight;
    private String properties;
}
