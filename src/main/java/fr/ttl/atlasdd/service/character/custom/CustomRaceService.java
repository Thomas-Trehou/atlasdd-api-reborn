package fr.ttl.atlasdd.service.character.custom;

import fr.ttl.atlasdd.apidto.character.custom.CustomRaceApiDto;
import org.springframework.stereotype.Service;

@Service
public interface CustomRaceService {

    CustomRaceApiDto createRace(CustomRaceApiDto raceApiDto);

    CustomRaceApiDto updateRace(CustomRaceApiDto raceApiDto);
}
