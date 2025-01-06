package fr.ttl.atlasdd.repository;

import fr.ttl.atlasdd.sqldto.ArmorSqlDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArmorRepo extends JpaRepository<ArmorSqlDto, Long> {
}
