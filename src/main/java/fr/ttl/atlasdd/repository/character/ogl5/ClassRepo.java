package fr.ttl.atlasdd.repository.character.ogl5;

import fr.ttl.atlasdd.entity.character.ogl5.Ogl5Class;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassRepo extends JpaRepository<Ogl5Class, Long> {
}
