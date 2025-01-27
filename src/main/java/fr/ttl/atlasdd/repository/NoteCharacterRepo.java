package fr.ttl.atlasdd.repository;

import fr.ttl.atlasdd.sqldto.NoteCharacterSqlDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoteCharacterRepo  extends JpaRepository<NoteCharacterSqlDto, Long> {

    List<NoteCharacterSqlDto> findAllByOgl5CharacterSheet_Id(Long characterSheetId);

    List<NoteCharacterSqlDto> findAllByCustomCharacterSheet_Id(Long characterSheetId);
}
