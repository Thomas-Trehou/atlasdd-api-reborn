package fr.ttl.atlasdd.sqldto;

import fr.ttl.atlasdd.utils.ArmorCategory;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table(name = "armors")
public class ArmorSqlDto extends BaseSqlDto {

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

    @Lob
    @Column(columnDefinition = "TEXT")
    private String properties;

    @OneToOne(mappedBy = "armor")
    private CharacterSheetSqlDto characterSheet;
}
