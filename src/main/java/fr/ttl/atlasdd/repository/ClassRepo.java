package fr.ttl.atlasdd.repository;

import fr.ttl.atlasdd.sqldto.ClassSqlDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassRepo extends JpaRepository<ClassSqlDto, Long> {
}
