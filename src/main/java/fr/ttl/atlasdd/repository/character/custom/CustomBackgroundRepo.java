package fr.ttl.atlasdd.repository.character.custom;

import fr.ttl.atlasdd.sqldto.character.custom.CustomBackgroundSqlDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomBackgroundRepo extends JpaRepository<CustomBackgroundSqlDto, Long> {
}
