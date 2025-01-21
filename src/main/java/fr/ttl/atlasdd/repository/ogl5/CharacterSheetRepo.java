package fr.ttl.atlasdd.repository.ogl5;

import fr.ttl.atlasdd.sqldto.ogl5.CharacterSheetSqlDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CharacterSheetRepo extends JpaRepository<CharacterSheetSqlDto, Long> {

    List<CharacterSheetSqlDto> findAllByOwner_Id(Long userId);
}
