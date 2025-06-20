package fr.ttl.atlasdd.entity.character.custom;

import fr.ttl.atlasdd.entity.BaseEntity;
import fr.ttl.atlasdd.entity.character.ogl5.Ogl5Skill;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "custom_character_skills")
public class CustomCharacterSkill extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "character_id")
    private CustomCharacterSheet characterSheet;

    @ManyToOne
    @JoinColumn(name = "skill_id")
    private CustomSkill skill;

    @Column(name = "is_expert")
    private boolean expert = false;
}
