package fr.ttl.atlasdd.repository;

import fr.ttl.atlasdd.sqldto.SpellSqlDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpellRepo extends JpaRepository<SpellSqlDto, Long> {
}
