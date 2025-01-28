package fr.ttl.atlasdd.sqldto;

import fr.ttl.atlasdd.sqldto.ogl5.CharacterSheetSqlDto;
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
@Table(name = "users")
public class UserSqlDto extends BaseSqlDto{

    private String pseudo;
    private String slug;
    private String email;
    private String password;

    @OneToMany(mappedBy = "owner")
    private List<CharacterSheetSqlDto> characterSheetList;

    @ManyToMany
    @JoinTable(
            name = "users_have_friends",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "friend_id")
    )
    private List<UserSqlDto> friends;

}
