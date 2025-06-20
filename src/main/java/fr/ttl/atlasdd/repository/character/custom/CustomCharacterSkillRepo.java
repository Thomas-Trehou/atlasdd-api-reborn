package fr.ttl.atlasdd.repository.character.custom;

import fr.ttl.atlasdd.entity.character.custom.CustomCharacterSkill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomCharacterSkillRepo extends JpaRepository<CustomCharacterSkill, Long> {

    @Modifying
    @Query("DELETE FROM CustomCharacterSkill cs WHERE cs.characterSheet.id = :characterId")
    void deleteByCharacterSheetId(@Param("characterId") Long characterId);
}
