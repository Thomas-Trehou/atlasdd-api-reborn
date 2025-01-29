package fr.ttl.atlasdd.apidto.character.ogl5;

import fr.ttl.atlasdd.apidto.BaseApiDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ClassApiDto extends BaseApiDto {

    private String name;
    private String hitDice;
    private int startingHitPoints;
    private String startingEquipment;

    private List<SpellApiDto> classSpells;
}
