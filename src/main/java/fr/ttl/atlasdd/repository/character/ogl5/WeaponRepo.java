package fr.ttl.atlasdd.repository.character.ogl5;

import fr.ttl.atlasdd.entity.character.ogl5.Ogl5Weapon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WeaponRepo extends JpaRepository<Ogl5Weapon, Long> {
}
