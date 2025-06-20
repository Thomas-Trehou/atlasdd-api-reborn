package fr.ttl.atlasdd.repository.character.custom;

import fr.ttl.atlasdd.entity.character.custom.CustomCharacterSheet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomCharacterSheetRepo extends JpaRepository<CustomCharacterSheet, Long> {

    List<CustomCharacterSheet> findAllByOwner_Id(Long userId);

    @Query("SELECT DISTINCT cs FROM CustomCharacterSheet cs " +
            "LEFT JOIN FETCH cs.characterSkills csk " +
            "LEFT JOIN FETCH csk.skill " +
            "WHERE cs.id = :id")
    Optional<CustomCharacterSheet> findByIdWithSkills(@Param("id") Long id);
}
