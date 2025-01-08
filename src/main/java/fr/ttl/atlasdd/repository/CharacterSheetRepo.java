package fr.ttl.atlasdd.repository;

import fr.ttl.atlasdd.sqldto.CharacterSheetSqlDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CharacterSheetRepo extends JpaRepository<CharacterSheetSqlDto, Long> {

    List<CharacterSheetSqlDto> findAllByOwner_Id(Long userId);
}
