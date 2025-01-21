package fr.ttl.atlasdd.repository.ogl5;

import fr.ttl.atlasdd.sqldto.ogl5.RaceSqlDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RaceRepo extends JpaRepository<RaceSqlDto, Long> {
}
