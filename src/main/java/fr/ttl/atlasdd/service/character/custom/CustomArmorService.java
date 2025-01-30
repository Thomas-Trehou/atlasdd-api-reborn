package fr.ttl.atlasdd.service.character.custom;

import fr.ttl.atlasdd.apidto.character.custom.CustomArmorApiDto;
import org.springframework.stereotype.Service;

@Service
public interface CustomArmorService {

    CustomArmorApiDto createArmor(CustomArmorApiDto armorApiDto);

    CustomArmorApiDto updateArmor(CustomArmorApiDto armorApiDto);
}
