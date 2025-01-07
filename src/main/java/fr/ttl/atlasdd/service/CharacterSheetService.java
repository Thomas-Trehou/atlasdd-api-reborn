package fr.ttl.atlasdd.service;

import fr.ttl.atlasdd.apidto.CharacterSheetApiDto;
import fr.ttl.atlasdd.apidto.CharacterSheetRequestApiDto;
import org.springframework.stereotype.Service;

@Service
public interface CharacterSheetService {

    CharacterSheetApiDto createCharacterSheet(CharacterSheetRequestApiDto characterSheetRequestApiDto);
}
