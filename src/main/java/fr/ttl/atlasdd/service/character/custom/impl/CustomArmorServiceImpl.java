package fr.ttl.atlasdd.service.character.custom.impl;

import fr.ttl.atlasdd.apidto.character.custom.CustomArmorApiDto;
import fr.ttl.atlasdd.exception.character.custom.notfound.CustomArmorNotFoundException;
import fr.ttl.atlasdd.exception.character.custom.savingerror.CustomArmorSavingErrorException;
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

        try {
            return customArmorMapper.toApiDto(customArmorRepository.save(armorSqlDto));
        } catch (Exception e) {
            throw new CustomArmorSavingErrorException("Erreur lors de la sauvegarde de l'armure");
        }
    }

    @Override
    public CustomArmorApiDto updateArmor(CustomArmorApiDto armorApiDto) {

        CustomArmorSqlDto armorSqlDto = customArmorRepository.findById(armorApiDto.getId())
                .orElseThrow(() -> new CustomArmorNotFoundException("Armure non trouvée"));

        customArmorMapper.updateSqlDto(armorApiDto, armorSqlDto);

        try {
            return customArmorMapper.toApiDto(customArmorRepository.save(armorSqlDto));
        } catch (Exception e) {
            throw new CustomArmorSavingErrorException("Erreur lors de la sauvegarde de l'armure");
        }
    }

}
