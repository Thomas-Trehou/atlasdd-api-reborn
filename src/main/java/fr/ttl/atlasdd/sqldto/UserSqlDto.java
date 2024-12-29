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
@Table(name = "users")
public class UserSqlDto extends BaseSqlDto{

    private String pseudo;
    private String slug;
    private String email;
    private String password;

    @OneToMany(mappedBy = "owner")
    private List<CharacterSheetSqlDto> characterSheetList;

}
