package fr.ttl.atlasdd.service;

import fr.ttl.atlasdd.apidto.CharacterSheetApiDto;
import fr.ttl.atlasdd.apidto.CharacterSheetRequestApiDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CharacterSheetService {

    CharacterSheetApiDto createCharacterSheet(CharacterSheetRequestApiDto characterSheetRequestApiDto);

    CharacterSheetApiDto getCharacterSheetById(Long id);

    List<CharacterSheetApiDto> getCharacterSheetsByUserId(Long userId);
}
