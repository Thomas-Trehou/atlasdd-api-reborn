package fr.ttl.atlasdd.repository;

import fr.ttl.atlasdd.sqldto.SkillSqlDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SkillRepo extends JpaRepository<SkillSqlDto, Long> {
    SkillSqlDto findByName(String name);
}
