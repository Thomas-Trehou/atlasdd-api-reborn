package fr.ttl.atlasdd.entity.character.custom;

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
@Table(name = "custom_skills")
public class CustomSkill extends BaseEntity {

    private String name;

    @ManyToMany(mappedBy = "skills")
    private List<CustomCharacterSheet> characterSheets;
}
