package fr.ttl.atlasdd.repository.character.custom;

import fr.ttl.atlasdd.entity.character.custom.CustomCharacterSheet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomCharacterSheetRepo extends JpaRepository<CustomCharacterSheet, Long> {

    List<CustomCharacterSheet> findAllByOwner_Id(Long userId);
}
