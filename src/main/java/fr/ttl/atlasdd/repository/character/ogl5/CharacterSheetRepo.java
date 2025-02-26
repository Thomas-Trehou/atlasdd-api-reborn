package fr.ttl.atlasdd.repository.character.ogl5;

import fr.ttl.atlasdd.entity.character.ogl5.Ogl5CharacterSheet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CharacterSheetRepo extends JpaRepository<Ogl5CharacterSheet, Long> {

    List<Ogl5CharacterSheet> findAllByOwner_Id(Long userId);
}
