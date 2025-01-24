package fr.ttl.atlasdd.service.custom.impl;

import fr.ttl.atlasdd.apidto.custom.CustomRaceApiDto;
import fr.ttl.atlasdd.mapper.custom.CustomRaceMapper;
import fr.ttl.atlasdd.repository.custom.CustomRaceRepo;
import fr.ttl.atlasdd.service.custom.CustomRaceService;
import fr.ttl.atlasdd.sqldto.custom.CustomRaceSqlDto;
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
