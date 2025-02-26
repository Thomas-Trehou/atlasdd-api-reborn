package fr.ttl.atlasdd.service.character.custom.impl;

import fr.ttl.atlasdd.apidto.character.custom.CustomBackgroundApiDto;
import fr.ttl.atlasdd.exception.character.custom.notfound.CustomBackgroundNotFoundException;
import fr.ttl.atlasdd.exception.character.custom.savingerror.CustomBackgroundSavingErrorException;
import fr.ttl.atlasdd.mapper.character.custom.CustomBackgroundMapper;
import fr.ttl.atlasdd.repository.character.custom.CustomBackgroundRepo;
import fr.ttl.atlasdd.service.character.custom.CustomBackgroundService;
import fr.ttl.atlasdd.entity.character.custom.CustomBackground;
import fr.ttl.atlasdd.utils.exception.ExceptionMessage;
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

        CustomBackground customBackground = customBackgroundMapper.toSqlDto(customBackgroundApiDto);

        try {
            return customBackgroundMapper.toApiDto(customBackgroundRepository.save(customBackground));
        } catch (Exception e) {
            throw new CustomBackgroundSavingErrorException(ExceptionMessage.BACKGROUND_SAVE_ERROR.getMessage());
        }
    }

    @Override
    public CustomBackgroundApiDto updateBackground(CustomBackgroundApiDto customBackgroundApiDto) {

        CustomBackground customBackground = customBackgroundRepository.findById(customBackgroundApiDto.getId())
                .orElseThrow(() -> new CustomBackgroundNotFoundException(ExceptionMessage.BACKGROUND_NOT_FOUND.getMessage()));

        customBackgroundMapper.updateFromApiDto(customBackgroundApiDto, customBackground);

        try {
            return customBackgroundMapper.toApiDto(customBackgroundRepository.save(customBackground));
        } catch (Exception e) {
            throw new CustomBackgroundSavingErrorException(ExceptionMessage.BACKGROUND_UPDATE_ERROR.getMessage());
        }
    }
}
