package fr.ttl.atlasdd.service.character.custom.impl;

import fr.ttl.atlasdd.apidto.character.custom.CustomRaceApiDto;
import fr.ttl.atlasdd.exception.character.custom.notfound.CustomRaceNotFoundException;
import fr.ttl.atlasdd.exception.character.custom.savingerror.CustomRaceSavingErrorException;
import fr.ttl.atlasdd.mapper.character.custom.CustomRaceMapper;
import fr.ttl.atlasdd.repository.character.custom.CustomRaceRepo;
import fr.ttl.atlasdd.service.character.custom.CustomRaceService;
import fr.ttl.atlasdd.entity.character.custom.CustomRace;
import fr.ttl.atlasdd.utils.exception.ExceptionMessage;
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

        CustomRace raceSqlDto = customRaceMapper.toSqlDto(customRaceApiDto);

        try {
            return customRaceMapper.toApiDto(customRaceRepository.save(raceSqlDto));
        } catch (Exception e) {
            throw new CustomRaceSavingErrorException(ExceptionMessage.RACE_SAVE_ERROR.getMessage());
        }
    }

    @Override
    public CustomRaceApiDto updateRace(CustomRaceApiDto customRaceApiDto) {

        CustomRace raceSqlDto = customRaceRepository.findById(customRaceApiDto.getId())
                .orElseThrow(() -> new CustomRaceNotFoundException(ExceptionMessage.RACE_NOT_FOUND.getMessage()));

        customRaceMapper.updateFromApiDto(customRaceApiDto, raceSqlDto);

        try {
            return customRaceMapper.toApiDto(customRaceRepository.save(raceSqlDto));
        } catch (Exception e) {
            throw new CustomRaceSavingErrorException(ExceptionMessage.RACE_UPDATE_ERROR.getMessage());
        }
    }
}
