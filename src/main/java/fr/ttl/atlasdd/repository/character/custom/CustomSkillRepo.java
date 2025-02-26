package fr.ttl.atlasdd.repository.character.custom;

import fr.ttl.atlasdd.entity.character.custom.CustomSkill;
import fr.ttl.atlasdd.entity.character.ogl5.Ogl5Skill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomSkillRepo extends JpaRepository<CustomSkill, Long> {
    Ogl5Skill findByName(String name);
}
