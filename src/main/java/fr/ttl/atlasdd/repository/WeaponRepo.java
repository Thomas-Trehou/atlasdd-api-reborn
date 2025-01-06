package fr.ttl.atlasdd.repository;

import fr.ttl.atlasdd.sqldto.WeaponSqlDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WeaponRepo extends JpaRepository<WeaponSqlDto, Long> {
}
