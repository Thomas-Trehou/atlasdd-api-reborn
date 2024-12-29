package fr.ttl.atlasdd.sqldto;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table(name = "backgrounds")
public class BackgroundSqlDto extends BaseSqlDto{

    private String name;
    private String masteredTools;
    private String startingEquipment;
    private String backgroundFeature;

    @OneToMany(mappedBy = "background")
    private List<CharacterSheetSqlDto> characterSheets;
}
