package fr.ttl.atlasdd.service.ogl5;

import fr.ttl.atlasdd.apidto.ogl5.CharacterSheetApiDto;
import fr.ttl.atlasdd.apidto.ogl5.CharacterSheetRequestApiDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CharacterSheetService {

    CharacterSheetApiDto createCharacterSheet(CharacterSheetRequestApiDto characterSheetRequestApiDto);

    CharacterSheetApiDto getCharacterSheetById(Long id);

    List<CharacterSheetApiDto> getCharacterSheetsByUserId(Long userId);

    CharacterSheetApiDto updateCharacterSheet(Long id, CharacterSheetRequestApiDto characterSheetRequestApiDto);
}
