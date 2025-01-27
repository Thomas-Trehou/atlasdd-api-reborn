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
@Table(name = "custom_races")
public class CustomRaceSqlDto extends BaseSqlDto {

    private String name;
    private String speed;
    private String languages;

    @Column(columnDefinition = "TEXT")
    private String traits;

    @OneToMany(mappedBy = "race")
    private List<CustomCharacterSheetSqlDto> characterSheets;
}