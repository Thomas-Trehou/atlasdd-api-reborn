package fr.ttl.atlasdd.service.character.custom.impl;

import fr.ttl.atlasdd.apidto.character.custom.CustomWeaponApiDto;
import fr.ttl.atlasdd.exception.character.custom.CustomWeaponNotFoundException;
import fr.ttl.atlasdd.exception.character.custom.CustomWeaponSavingErrorException;
import fr.ttl.atlasdd.mapper.character.custom.CustomWeaponMapper;
import fr.ttl.atlasdd.repository.character.custom.CustomWeaponRepo;
import fr.ttl.atlasdd.service.character.custom.CustomWeaponService;
import fr.ttl.atlasdd.sqldto.character.custom.CustomWeaponSqlDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomWeaponServiceImpl implements CustomWeaponService {

    private final CustomWeaponRepo customWeaponRepository;
    private final CustomWeaponMapper customWeaponMapper;

    public CustomWeaponServiceImpl(
            CustomWeaponRepo customWeaponRepository,
            CustomWeaponMapper customWeaponMapper
    ) {
        this.customWeaponRepository = customWeaponRepository;
        this.customWeaponMapper = customWeaponMapper;
    }

    @Override
    public List<CustomWeaponApiDto> createWeapons(List<CustomWeaponApiDto> customWeaponApiDtos) {
        try {
            return customWeaponApiDtos.stream()
                    .map(customWeaponMapper::toSqlDto)
                    .map(customWeaponRepository::save)
                    .map(customWeaponMapper::toApiDto)
                    .collect(Collectors.toList());

        } catch (Exception e) {
            throw new CustomWeaponSavingErrorException("Erreur lors de la sauvegarde des armes", 500);
        }

    }

    @Override
    public List<CustomWeaponApiDto> updateWeapons(List<CustomWeaponApiDto> customWeaponApiDtos) {

        try {
            return customWeaponApiDtos.stream()
                    .map(customWeaponApiDto -> {
                        CustomWeaponSqlDto existingWeapon = customWeaponRepository.findById(customWeaponApiDto.getId())
                                .orElseThrow(() -> new CustomWeaponNotFoundException("Weapon not found: " + customWeaponApiDto.getId(), 404));
                        customWeaponMapper.updateSqlDto(customWeaponApiDto, existingWeapon);
                        return customWeaponRepository.save(existingWeapon);
                    })
                    .map(customWeaponMapper::toApiDto)
                    .collect(Collectors.toList());

        } catch (Exception e) {
            throw new CustomWeaponSavingErrorException("Erreur lors de la sauvegarde des armes", 500);
        }

    }
}
