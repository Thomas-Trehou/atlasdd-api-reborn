package fr.ttl.atlasdd.repository.character.ogl5;

import fr.ttl.atlasdd.entity.character.ogl5.Ogl5CharacterSkill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CharacterSkillRepo extends JpaRepository<Ogl5CharacterSkill, Long> {

    @Modifying
    @Query("DELETE FROM Ogl5CharacterSkill cs WHERE cs.characterSheet.id = :characterId")
    void deleteByCharacterSheetId(@Param("characterId") Long characterId);
}
