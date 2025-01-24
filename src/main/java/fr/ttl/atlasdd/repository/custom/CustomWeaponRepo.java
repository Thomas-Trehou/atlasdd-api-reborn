package fr.ttl.atlasdd.repository.custom;

import fr.ttl.atlasdd.sqldto.custom.CustomWeaponSqlDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomWeaponRepo extends JpaRepository<CustomWeaponSqlDto, Long> {
}
