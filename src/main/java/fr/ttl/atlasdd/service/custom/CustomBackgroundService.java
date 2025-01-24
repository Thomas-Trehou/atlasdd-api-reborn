package fr.ttl.atlasdd.service.custom;

import fr.ttl.atlasdd.apidto.custom.CustomBackgroundApiDto;
import org.springframework.stereotype.Service;

@Service
public interface CustomBackgroundService {

    CustomBackgroundApiDto createBackground(CustomBackgroundApiDto backgroundApiDto);

    CustomBackgroundApiDto updateBackground(CustomBackgroundApiDto backgroundApiDto);
}
