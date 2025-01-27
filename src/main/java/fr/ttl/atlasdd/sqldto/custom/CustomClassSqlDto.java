package fr.ttl.atlasdd.sqldto.custom;

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
@Table(name = "custom_classes")
public class CustomClassSqlDto extends BaseSqlDto {

    private String name;
    private String hitDice;
    private int startingHitPoints;

    @Column(columnDefinition = "TEXT")
    private String startingEquipment;

    @OneToMany(mappedBy = "classe")
    private List<CustomCharacterSheetSqlDto> characterSheets;
}
