package fr.ttl.atlasdd.service.character.custom.impl;

import fr.ttl.atlasdd.apidto.character.custom.CustomArmorApiDto;
import fr.ttl.atlasdd.mapper.character.custom.CustomArmorMapper;
import fr.ttl.atlasdd.repository.character.custom.CustomArmorRepo;
import fr.ttl.atlasdd.service.character.custom.CustomArmorService;
import fr.ttl.atlasdd.sqldto.character.custom.CustomArmorSqlDto;
import org.springframework.stereotype.Service;

@Service
public class CustomArmorServiceImpl implements CustomArmorService {

    private final CustomArmorRepo customArmorRepository;
    private final CustomArmorMapper customArmorMapper;

    public CustomArmorServiceImpl(
            CustomArmorRepo customArmorRepository,
            CustomArmorMapper customArmorMapper
    ) {
        this.customArmorRepository = customArmorRepository;
        this.customArmorMapper = customArmorMapper;
    }

    @Override
    public CustomArmorApiDto createArmor(CustomArmorApiDto armorApiDto) {

        CustomArmorSqlDto armorSqlDto = customArmorMapper.toSqlDto(armorApiDto);
        CustomArmorSqlDto savedArmorSqlDto = customArmorRepository.save(armorSqlDto);

        return customArmorMapper.toApiDto(savedArmorSqlDto);
    }

    @Override
    public CustomArmorApiDto updateArmor(CustomArmorApiDto armorApiDto) {

        CustomArmorSqlDto armorSqlDto = customArmorRepository.findById(armorApiDto.getId()).orElseThrow();
        customArmorMapper.updateSqlDto(armorApiDto, armorSqlDto);
        CustomArmorSqlDto savedArmorSqlDto = customArmorRepository.save(armorSqlDto);

        return customArmorMapper.toApiDto(savedArmorSqlDto);
    }

}
