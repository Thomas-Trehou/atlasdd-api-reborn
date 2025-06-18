package fr.ttl.atlasdd.repository.character.ogl5;

import fr.ttl.atlasdd.entity.character.ogl5.Ogl5CharacterSheet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CharacterSheetRepo extends JpaRepository<Ogl5CharacterSheet, Long> {

    List<Ogl5CharacterSheet> findAllByOwner_Id(Long userId);

    @Query("SELECT DISTINCT cs FROM Ogl5CharacterSheet cs " +
            "LEFT JOIN FETCH cs.characterSkills csk " +
            "LEFT JOIN FETCH csk.skill " +
            "WHERE cs.id = :id")
    Optional<Ogl5CharacterSheet> findByIdWithSkills(@Param("id") Long id);

}
