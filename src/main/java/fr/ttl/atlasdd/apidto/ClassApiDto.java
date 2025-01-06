package fr.ttl.atlasdd.apidto;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ClassApiDto extends BaseApiDto{

    private String name;
    private String hitDice;
    private int startingHitPoints;
    private String startingEquipment;

    private List<SpellApiDto> classSpells;
}
