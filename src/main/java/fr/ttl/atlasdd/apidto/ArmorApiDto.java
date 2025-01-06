package fr.ttl.atlasdd.apidto;

import fr.ttl.atlasdd.sqldto.CharacterSheetSqlDto;
import fr.ttl.atlasdd.utils.ArmorCategory;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ArmorApiDto extends BaseApiDto {

    private String index;
    private String name;

    @Enumerated(EnumType.STRING)
    private ArmorCategory armorCategory;

    private int armorClass;
    private int strengthMinimum;
    private boolean stealthDisadvantage;
    private BigDecimal weight;
    private String cost;
    private String properties;
}
