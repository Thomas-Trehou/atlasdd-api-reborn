package fr.ttl.atlasdd.service.character.custom.impl;

import fr.ttl.atlasdd.apidto.character.custom.CustomArmorApiDto;
import fr.ttl.atlasdd.entity.character.custom.CustomArmor;
import fr.ttl.atlasdd.exception.character.custom.notfound.CustomArmorNotFoundException;
import fr.ttl.atlasdd.exception.character.custom.savingerror.CustomArmorSavingErrorException;
import fr.ttl.atlasdd.mapper.character.custom.CustomArmorMapper;
import fr.ttl.atlasdd.repository.character.custom.CustomArmorRepo;
import fr.ttl.atlasdd.service.character.custom.CustomArmorService;
import fr.ttl.atlasdd.utils.exception.ExceptionMessage;
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

        CustomArmor armorSqlDto = customArmorMapper.toSqlDto(armorApiDto);

        try {
            return customArmorMapper.toApiDto(customArmorRepository.save(armorSqlDto));
        } catch (Exception e) {
            throw new CustomArmorSavingErrorException(ExceptionMessage.ARMOR_SAVE_ERROR.getMessage());
        }
    }

    @Override
    public CustomArmorApiDto updateArmor(CustomArmorApiDto armorApiDto) {

        CustomArmor armorSqlDto = customArmorRepository.findById(armorApiDto.getId())
                .orElseThrow(() -> new CustomArmorNotFoundException(ExceptionMessage.ARMOR_NOT_FOUND.getMessage()));

        customArmorMapper.updateSqlDto(armorApiDto, armorSqlDto);

        try {
            return customArmorMapper.toApiDto(customArmorRepository.save(armorSqlDto));
        } catch (Exception e) {
            throw new CustomArmorSavingErrorException(ExceptionMessage.ARMOR_UPDATE_ERROR.getMessage());
        }
    }

}
