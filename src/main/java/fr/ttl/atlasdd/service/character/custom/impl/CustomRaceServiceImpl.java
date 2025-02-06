package fr.ttl.atlasdd.service.character.custom.impl;

import fr.ttl.atlasdd.apidto.character.custom.CustomRaceApiDto;
import fr.ttl.atlasdd.exception.character.custom.notfound.CustomRaceNotFoundException;
import fr.ttl.atlasdd.exception.character.custom.savingerror.CustomRaceSavingErrorException;
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

        try {
            return customRaceMapper.toApiDto(customRaceRepository.save(raceSqlDto));
        } catch (Exception e) {
            throw new CustomRaceSavingErrorException("Erreur lors de la sauvegarde de la race");
        }
    }

    @Override
    public CustomRaceApiDto updateRace(CustomRaceApiDto customRaceApiDto) {

        CustomRaceSqlDto raceSqlDto = customRaceRepository.findById(customRaceApiDto.getId())
                .orElseThrow(() -> new CustomRaceNotFoundException("Race non trouvée"));

        customRaceMapper.updateFromApiDto(customRaceApiDto, raceSqlDto);

        try {
            return customRaceMapper.toApiDto(customRaceRepository.save(raceSqlDto));
        } catch (Exception e) {
            throw new CustomRaceSavingErrorException("Erreur lors de la sauvegarde de la race");
        }
    }
}
