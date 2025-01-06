package fr.ttl.atlasdd.repository;

import fr.ttl.atlasdd.sqldto.RaceSqlDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RaceRepo extends JpaRepository<RaceSqlDto, Long> {
}
