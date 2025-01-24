package fr.ttl.atlasdd.service.custom.impl;

import fr.ttl.atlasdd.apidto.custom.CustomBackgroundApiDto;
import fr.ttl.atlasdd.mapper.custom.CustomBackgroundMapper;
import fr.ttl.atlasdd.repository.custom.CustomBackgroundRepo;
import fr.ttl.atlasdd.service.custom.CustomBackgroundService;
import fr.ttl.atlasdd.sqldto.custom.CustomBackgroundSqlDto;
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
        CustomBackgroundSqlDto savedCustomBackgroundSqlDto = customBackgroundRepository.save(customBackgroundSqlDto);

        return customBackgroundMapper.toApiDto(savedCustomBackgroundSqlDto);
    }

    @Override
    public CustomBackgroundApiDto updateBackground(CustomBackgroundApiDto customBackgroundApiDto) {

        CustomBackgroundSqlDto customBackgroundSqlDto = customBackgroundRepository.findById(customBackgroundApiDto.getId()).orElseThrow();
        customBackgroundMapper.updateFromApiDto(customBackgroundApiDto, customBackgroundSqlDto);
        CustomBackgroundSqlDto savedCustomBackgroundSqlDto = customBackgroundRepository.save(customBackgroundSqlDto);

        return customBackgroundMapper.toApiDto(savedCustomBackgroundSqlDto);
    }
}
