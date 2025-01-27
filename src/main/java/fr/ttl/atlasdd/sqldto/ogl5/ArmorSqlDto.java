package fr.ttl.atlasdd.sqldto.ogl5;

import fr.ttl.atlasdd.sqldto.BaseSqlDto;
import fr.ttl.atlasdd.utils.ArmorCategory;
import jakarta.persistence.*;
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
@Table(name = "ogl5_armors")
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

    @Column(columnDefinition = "TEXT")
    private String properties;

    @OneToMany(mappedBy = "armor")
    private List<CharacterSheetSqlDto> characterSheet;
}
