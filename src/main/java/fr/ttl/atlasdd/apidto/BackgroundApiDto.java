package fr.ttl.atlasdd.apidto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class BackgroundApiDto  extends BaseApiDto{

    private String name;
    private String masteredTools;
    private String startingEquipment;
    private String backgroundFeature;
}
