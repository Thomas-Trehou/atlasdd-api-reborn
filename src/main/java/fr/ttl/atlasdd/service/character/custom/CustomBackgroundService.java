package fr.ttl.atlasdd.service.character.custom;

import fr.ttl.atlasdd.apidto.character.custom.CustomBackgroundApiDto;
import org.springframework.stereotype.Service;

@Service
public interface CustomBackgroundService {

    CustomBackgroundApiDto createBackground(CustomBackgroundApiDto backgroundApiDto);

    CustomBackgroundApiDto updateBackground(CustomBackgroundApiDto backgroundApiDto);
}
