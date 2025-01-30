package fr.ttl.atlasdd.sqldto.character.ogl5;

import fr.ttl.atlasdd.sqldto.BaseSqlDto;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
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
@Table(name = "ogl5_skills")
public class SkillSqlDto extends BaseSqlDto {

    private String name;

    @ManyToMany(mappedBy = "skills")
    private List<CharacterSheetSqlDto> characterSheets;
}
