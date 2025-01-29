package fr.ttl.atlasdd.service.character.custom.impl;

import fr.ttl.atlasdd.apidto.character.custom.CustomRaceApiDto;
import fr.ttl.atlasdd.mapper.character.custom.CustomRaceMapper;
import fr.ttl.atlasdd.repository.character.custom.CustomRaceRepo;
import fr.ttl.atlasdd.service.character.custom.CustomRaceService;
import fr.ttl.atlasdd.sqldto.character.custom.CustomRaceSqlDto;
import org.springframework.stereotype.Service;

@Service
public class CustomRaceServiceImpl implements CustomRaceService {

    private final CustomRaceRepo customRaceRepository;
    private final CustomRaceMapper customRaceMapper;

    public CustomRaceServiceImpl(
            CustomRaceRepo customRaceRepository,
            CustomRaceMapper customRaceMapper
    ) {
        this.customRaceRepository = customRaceRepository;
        this.customRaceMapper = customRaceMapper;
    }

    @Override
    public CustomRaceApiDto createRace(CustomRaceApiDto customRaceApiDto) {

        CustomRaceSqlDto raceSqlDto = customRaceMapper.toSqlDto(customRaceApiDto);
        CustomRaceSqlDto savedRaceSqlDto = customRaceRepository.save(raceSqlDto);

        return customRaceMapper.toApiDto(savedRaceSqlDto);
    }

    @Override
    public CustomRaceApiDto updateRace(CustomRaceApiDto customRaceApiDto) {

        CustomRaceSqlDto raceSqlDto = customRaceRepository.findById(customRaceApiDto.getId()).orElseThrow();
        customRaceMapper.updateFromApiDto(customRaceApiDto, raceSqlDto);
        CustomRaceSqlDto savedRaceSqlDto = customRaceRepository.save(raceSqlDto);

        return customRaceMapper.toApiDto(savedRaceSqlDto);
    }
}
