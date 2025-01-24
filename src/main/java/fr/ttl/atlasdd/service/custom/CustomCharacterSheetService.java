package fr.ttl.atlasdd.service.custom;

import fr.ttl.atlasdd.apidto.custom.CustomCharacterSheetApiDto;
import fr.ttl.atlasdd.apidto.custom.CustomCharacterSheetCreateRequestApiDto;
import fr.ttl.atlasdd.apidto.custom.CustomCharacterSheetUpdateRequestApiDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CustomCharacterSheetService {

    CustomCharacterSheetApiDto createCharacterSheet(CustomCharacterSheetCreateRequestApiDto characterSheetCreateRequestApiDto);

    CustomCharacterSheetApiDto getCharacterSheetById(Long id);

    List<CustomCharacterSheetApiDto> getCharacterSheetsByUserId(Long userId);

    CustomCharacterSheetApiDto updateCharacterSheet(Long id, CustomCharacterSheetUpdateRequestApiDto characterSheetApiDto);
}
