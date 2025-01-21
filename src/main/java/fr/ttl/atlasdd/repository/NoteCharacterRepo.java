package fr.ttl.atlasdd.repository;

import fr.ttl.atlasdd.sqldto.NoteCharacterSqlDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoteCharacterRepo  extends JpaRepository<NoteCharacterSqlDto, Long> {
}
