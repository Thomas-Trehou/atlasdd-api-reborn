package fr.ttl.atlasdd.service.character.custom;

import fr.ttl.atlasdd.apidto.character.custom.CustomWeaponApiDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CustomWeaponService {

    List<CustomWeaponApiDto> createWeapons(List<CustomWeaponApiDto> customWeaponApiDtos);

    List<CustomWeaponApiDto> updateWeapons(List<CustomWeaponApiDto> customWeaponApiDtos);
}
