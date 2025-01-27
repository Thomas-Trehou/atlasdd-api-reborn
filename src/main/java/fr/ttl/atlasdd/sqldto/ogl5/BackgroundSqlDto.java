package fr.ttl.atlasdd.sqldto.ogl5;

import fr.ttl.atlasdd.sqldto.BaseSqlDto;
import jakarta.persistence.*;
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
@Table(name = "ogl5_backgrounds")
public class BackgroundSqlDto extends BaseSqlDto {

    private String name;
    private String masteredTools;

    @Column(columnDefinition = "TEXT")
    private String startingEquipment;

    private String backgroundFeature;

    @OneToMany(mappedBy = "background")
    private List<CharacterSheetSqlDto> characterSheets;
}
