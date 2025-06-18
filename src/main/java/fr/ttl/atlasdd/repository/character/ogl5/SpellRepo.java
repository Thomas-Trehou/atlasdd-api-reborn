package fr.ttl.atlasdd.repository.character.ogl5;

import fr.ttl.atlasdd.entity.character.ogl5.Ogl5Spell;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpellRepo extends JpaRepository<Ogl5Spell, Long> {
}
