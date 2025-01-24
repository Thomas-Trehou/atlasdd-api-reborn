package fr.ttl.atlasdd.service.custom;

import fr.ttl.atlasdd.apidto.custom.CustomRaceApiDto;
import org.springframework.stereotype.Service;

@Service
public interface CustomRaceService {

    CustomRaceApiDto createRace(CustomRaceApiDto raceApiDto);

    CustomRaceApiDto updateRace(CustomRaceApiDto raceApiDto);
}
