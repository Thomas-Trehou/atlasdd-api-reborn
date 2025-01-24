package fr.ttl.atlasdd.repository.custom;

import fr.ttl.atlasdd.sqldto.custom.CustomSpellSqlDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomSpellRepo extends JpaRepository<CustomSpellSqlDto, Long> {
}
