package fr.ttl.atlasdd.entity.character.ogl5;

import fr.ttl.atlasdd.entity.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ogl5_skills")
public class Ogl5Skill extends BaseEntity {

    private String name;

    @ManyToMany(mappedBy = "skills")
    private List<Ogl5CharacterSheet> characterSheets;
}
