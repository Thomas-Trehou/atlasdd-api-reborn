package fr.ttl.atlasdd.service.character.custom;

import fr.ttl.atlasdd.apidto.character.custom.CustomCharacterSheetApiDto;
import fr.ttl.atlasdd.apidto.character.custom.CustomCharacterSheetCreateRequestApiDto;
import fr.ttl.atlasdd.apidto.character.custom.CustomCharacterSheetUpdateRequestApiDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CustomCharacterSheetService {

    CustomCharacterSheetApiDto createCharacterSheet(CustomCharacterSheetCreateRequestApiDto characterSheetCreateRequestApiDto);

    CustomCharacterSheetApiDto getCharacterSheetById(Long id);

    List<CustomCharacterSheetApiDto> getCharacterSheetsByUserId(Long userId);

    CustomCharacterSheetApiDto updateCharacterSheet(Long id, CustomCharacterSheetUpdateRequestApiDto characterSheetApiDto);
}
