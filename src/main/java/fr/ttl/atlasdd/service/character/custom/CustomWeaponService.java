package fr.ttl.atlasdd.service.character.custom;

import fr.ttl.atlasdd.apidto.character.WeaponApiDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CustomWeaponService {

    List<WeaponApiDto> createWeapons(List<WeaponApiDto> customWeaponApiDtos);

    List<WeaponApiDto> updateWeapons(List<WeaponApiDto> customWeaponApiDtos);
}
