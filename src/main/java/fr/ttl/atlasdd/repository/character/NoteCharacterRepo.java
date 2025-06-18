package fr.ttl.atlasdd.repository.character;

import fr.ttl.atlasdd.entity.character.CharacterNote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoteCharacterRepo  extends JpaRepository<CharacterNote, Long> {

    List<CharacterNote> findAllByOgl5CharacterSheet_Id(Long characterSheetId);

    List<CharacterNote> findAllByCustomCharacterSheet_Id(Long characterSheetId);
}
