package fr.ttl.atlasdd.repository.custom;

import fr.ttl.atlasdd.sqldto.custom.CustomSkillSqlDto;
import fr.ttl.atlasdd.sqldto.ogl5.SkillSqlDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomSkillRepo extends JpaRepository<CustomSkillSqlDto, Long> {
    SkillSqlDto findByName(String name);
}
