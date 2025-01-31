package fr.ttl.atlasdd.service.character.custom.impl;

import fr.ttl.atlasdd.apidto.character.custom.CustomBackgroundApiDto;
import fr.ttl.atlasdd.exception.character.custom.notfound.CustomBackgroundNotFoundException;
import fr.ttl.atlasdd.exception.character.custom.savingerror.CustomBackgroundSavingErrorException;
import fr.ttl.atlasdd.mapper.character.custom.CustomBackgroundMapper;
import fr.ttl.atlasdd.repository.character.custom.CustomBackgroundRepo;
import fr.ttl.atlasdd.service.character.custom.CustomBackgroundService;
import fr.ttl.atlasdd.sqldto.character.custom.CustomBackgroundSqlDto;
import org.springframework.stereotype.Service;

@Service
public class CustomBackgroundServiceImpl implements CustomBackgroundService {

    private final CustomBackgroundRepo customBackgroundRepository;
    private final CustomBackgroundMapper customBackgroundMapper;

    public CustomBackgroundServiceImpl(
            CustomBackgroundRepo customBackgroundRepository,
            CustomBackgroundMapper customBackgroundMapper
    ) {
        this.customBackgroundRepository = customBackgroundRepository;
        this.customBackgroundMapper = customBackgroundMapper;
    }

    @Override
    public CustomBackgroundApiDto createBackground(CustomBackgroundApiDto customBackgroundApiDto) {

        CustomBackgroundSqlDto customBackgroundSqlDto = customBackgroundMapper.toSqlDto(customBackgroundApiDto);

        try {
            return customBackgroundMapper.toApiDto(customBackgroundRepository.save(customBackgroundSqlDto));
        } catch (Exception e) {
            throw new CustomBackgroundSavingErrorException("Erreur lors de la sauvegarde du background", 500);
        }
    }

    @Override
    public CustomBackgroundApiDto updateBackground(CustomBackgroundApiDto customBackgroundApiDto) {

        CustomBackgroundSqlDto customBackgroundSqlDto = customBackgroundRepository.findById(customBackgroundApiDto.getId())
                .orElseThrow(() -> new CustomBackgroundNotFoundException("Background non trouvé", 404));

        customBackgroundMapper.updateFromApiDto(customBackgroundApiDto, customBackgroundSqlDto);

        try {
            return customBackgroundMapper.toApiDto(customBackgroundRepository.save(customBackgroundSqlDto));
        } catch (Exception e) {
            throw new CustomBackgroundSavingErrorException("Erreur lors de la sauvegarde du background", 500);
        }
    }
}
