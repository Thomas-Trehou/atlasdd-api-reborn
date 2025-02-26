package fr.ttl.atlasdd.repository.character.custom;

import fr.ttl.atlasdd.entity.character.custom.CustomBackground;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomBackgroundRepo extends JpaRepository<CustomBackground, Long> {
}
