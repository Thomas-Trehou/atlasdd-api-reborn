package fr.ttl.atlasdd.service.character.custom.impl;

import fr.ttl.atlasdd.apidto.character.WeaponApiDto;
import fr.ttl.atlasdd.entity.character.custom.CustomWeapon;
import fr.ttl.atlasdd.exception.character.custom.notfound.CustomWeaponNotFoundException;
import fr.ttl.atlasdd.exception.character.custom.savingerror.CustomWeaponSavingErrorException;
import fr.ttl.atlasdd.mapper.character.custom.CustomWeaponMapper;
import fr.ttl.atlasdd.repository.character.custom.CustomWeaponRepo;
import fr.ttl.atlasdd.service.character.custom.CustomWeaponService;
import fr.ttl.atlasdd.utils.exception.ExceptionMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomWeaponServiceImpl implements CustomWeaponService {

    private final CustomWeaponRepo customWeaponRepository;
    private final CustomWeaponMapper customWeaponMapper;

    @Override
    public List<WeaponApiDto> createWeapons(List<WeaponApiDto> customWeaponApiDtos) {
        try {
            return customWeaponApiDtos.stream()
                    .map(customWeaponMapper::toEntity)
                    .map(customWeaponRepository::save)
                    .map(customWeaponMapper::toApiDto)
                    .collect(Collectors.toList());

        } catch (Exception e) {
            throw new CustomWeaponSavingErrorException(ExceptionMessage.WEAPON_SAVE_ERROR.getMessage());
        }

    }

    @Override
    public List<WeaponApiDto> updateWeapons(List<WeaponApiDto> customWeaponApiDtos) {

        try {
            return customWeaponApiDtos.stream()
                    .map(customWeaponApiDto -> {
                        CustomWeapon existingWeapon = customWeaponRepository.findById(customWeaponApiDto.getId())
                                .orElseThrow(() -> new CustomWeaponNotFoundException( ExceptionMessage.WEAPON_NOT_FOUND.getMessage() + " " + customWeaponApiDto.getId()));
                        customWeaponMapper.updateSqlDto(customWeaponApiDto, existingWeapon);
                        return customWeaponRepository.save(existingWeapon);
                    })
                    .map(customWeaponMapper::toApiDto)
                    .collect(Collectors.toList());

        } catch (Exception e) {
            throw new CustomWeaponSavingErrorException(ExceptionMessage.WEAPON_UPDATE_ERROR.getMessage());
        }

    }
}
