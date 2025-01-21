package fr.ttl.atlasdd.repository.ogl5;

import fr.ttl.atlasdd.sqldto.ogl5.BackgroundSqlDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BackgroundRepo extends JpaRepository<BackgroundSqlDto, Long> {
}
