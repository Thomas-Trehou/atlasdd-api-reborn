package fr.ttl.atlasdd.repository.custom;

import fr.ttl.atlasdd.sqldto.custom.CustomCharacterSheetSqlDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomCharacterSheetRepo extends JpaRepository<CustomCharacterSheetSqlDto, Long> {

    List<CustomCharacterSheetSqlDto> findAllByOwner_Id(Long userId);
}
