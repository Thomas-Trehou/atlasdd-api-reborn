package fr.ttl.atlasdd.service.character.ogl5;

import fr.ttl.atlasdd.apidto.character.ogl5.CharacterSheetApiDto;
import fr.ttl.atlasdd.apidto.character.ogl5.CharacterSheetCreateRequestApiDto;
import fr.ttl.atlasdd.apidto.character.ogl5.CharacterSheetUpdateRequestApiDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CharacterSheetService {

    CharacterSheetApiDto createCharacterSheet(CharacterSheetCreateRequestApiDto characterSheetCreateRequestApiDto);

    CharacterSheetApiDto getCharacterSheetById(Long id);

    List<CharacterSheetApiDto> getCharacterSheetsByUserId(Long userId);

    CharacterSheetApiDto updateCharacterSheet(Long id, CharacterSheetUpdateRequestApiDto characterSheetUpdateRequestApiDto);

    void deleteCharacterSheet(Long id);
}
