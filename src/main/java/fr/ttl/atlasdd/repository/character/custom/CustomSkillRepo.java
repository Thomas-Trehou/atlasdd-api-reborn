package fr.ttl.atlasdd.repository.character.custom;

import fr.ttl.atlasdd.sqldto.character.custom.CustomSkillSqlDto;
import fr.ttl.atlasdd.sqldto.character.ogl5.SkillSqlDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomSkillRepo extends JpaRepository<CustomSkillSqlDto, Long> {
    SkillSqlDto findByName(String name);
}
