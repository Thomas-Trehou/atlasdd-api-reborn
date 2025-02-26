package fr.ttl.atlasdd.repository.character.custom;

import fr.ttl.atlasdd.entity.character.custom.CustomArmor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomArmorRepo extends JpaRepository<CustomArmor, Long> {
}
