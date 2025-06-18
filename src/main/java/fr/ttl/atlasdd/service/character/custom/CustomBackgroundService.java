package fr.ttl.atlasdd.service.character.custom;

import fr.ttl.atlasdd.apidto.character.BackgroundApiDto;
import org.springframework.stereotype.Service;

@Service
public interface CustomBackgroundService {

    BackgroundApiDto createBackground(BackgroundApiDto backgroundApiDto);

    BackgroundApiDto updateBackground(BackgroundApiDto backgroundApiDto);
}
