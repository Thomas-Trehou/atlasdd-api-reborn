package fr.ttl.atlasdd.entity.character.custom;

import fr.ttl.atlasdd.entity.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
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

    @OneToMany(mappedBy = "skill")
    private List<CustomCharacterSkill> characterSkills;

    public List<CustomSkill> getSkills() {
        return characterSkills != null ?
                characterSkills.stream().map(CustomCharacterSkill::getSkill).toList() :
                List.of();
    }

    public List<CustomSkill> getExpertSkills() {
        return characterSkills != null ?
                characterSkills.stream()
                        .filter(CustomCharacterSkill::isExpert)
                        .map(CustomCharacterSkill::getSkill)
                        .toList() :
                List.of();
    }

    public boolean hasSkillProficiency(CustomSkill skill) {
        return characterSkills != null &&
                characterSkills.stream()
                        .anyMatch(cs -> cs.getSkill().equals(skill));
    }

    public boolean hasSkillExpertise(CustomSkill skill) {
        return characterSkills != null &&
                characterSkills.stream()
                        .anyMatch(cs -> cs.getSkill().equals(skill) && cs.isExpert());
    }

}
