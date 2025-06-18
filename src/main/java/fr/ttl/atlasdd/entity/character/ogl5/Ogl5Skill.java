package fr.ttl.atlasdd.entity.character.ogl5;

import fr.ttl.atlasdd.entity.BaseEntity;
import jakarta.persistence.*;
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

    @OneToMany(mappedBy = "skill")
    private List<Ogl5CharacterSkill> characterSkills;

    public List<Ogl5Skill> getSkills() {
        return characterSkills != null ?
                characterSkills.stream().map(Ogl5CharacterSkill::getSkill).toList() :
                List.of();
    }

    public List<Ogl5Skill> getExpertSkills() {
        return characterSkills != null ?
                characterSkills.stream()
                        .filter(Ogl5CharacterSkill::isExpert)
                        .map(Ogl5CharacterSkill::getSkill)
                        .toList() :
                List.of();
    }

    public boolean hasSkillProficiency(Ogl5Skill skill) {
        return characterSkills != null &&
                characterSkills.stream()
                        .anyMatch(cs -> cs.getSkill().equals(skill));
    }

    public boolean hasSkillExpertise(Ogl5Skill skill) {
        return characterSkills != null &&
                characterSkills.stream()
                        .anyMatch(cs -> cs.getSkill().equals(skill) && cs.isExpert());
    }

}
