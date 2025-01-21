package fr.ttl.atlasdd.repository.ogl5;

import fr.ttl.atlasdd.sqldto.ogl5.ClassSqlDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassRepo extends JpaRepository<ClassSqlDto, Long> {
}
