package fr.ttl.atlasdd.repository.custom;

import fr.ttl.atlasdd.sqldto.custom.CustomClassSqlDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomClassRepo extends JpaRepository<CustomClassSqlDto, Long> {
}
