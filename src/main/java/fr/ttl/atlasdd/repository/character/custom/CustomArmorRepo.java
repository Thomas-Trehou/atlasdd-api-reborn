package fr.ttl.atlasdd.repository.character.custom;

import fr.ttl.atlasdd.sqldto.character.custom.CustomArmorSqlDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomArmorRepo extends JpaRepository<CustomArmorSqlDto, Long> {
}
