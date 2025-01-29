package fr.ttl.atlasdd.repository.character.custom;

import fr.ttl.atlasdd.sqldto.character.custom.CustomRaceSqlDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomRaceRepo extends JpaRepository<CustomRaceSqlDto, Long> {
}
