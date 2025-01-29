package fr.ttl.atlasdd.repository.character.ogl5;

import fr.ttl.atlasdd.sqldto.character.ogl5.WeaponSqlDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WeaponRepo extends JpaRepository<WeaponSqlDto, Long> {
}
