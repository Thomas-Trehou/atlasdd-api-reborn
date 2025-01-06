package fr.ttl.atlasdd.repository;

import fr.ttl.atlasdd.sqldto.BackgroundSqlDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BackgroundRepo extends JpaRepository<BackgroundSqlDto, Long> {
}
