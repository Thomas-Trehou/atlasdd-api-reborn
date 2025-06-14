package fr.ttl.atlasdd.entity.character.ogl5;

import fr.ttl.atlasdd.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ogl5_character_skills")
public class Ogl5CharacterSkill extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "character_id")
    private Ogl5CharacterSheet characterSheet;

    @ManyToOne
    @JoinColumn(name = "skill_id")
    private Ogl5Skill skill;

    @Column(name = "is_expert")
    private boolean expert = false;
}