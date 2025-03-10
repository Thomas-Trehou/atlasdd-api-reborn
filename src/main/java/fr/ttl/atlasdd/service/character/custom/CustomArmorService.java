package fr.ttl.atlasdd.service.character.custom;

import fr.ttl.atlasdd.apidto.character.ArmorApiDto;
import org.springframework.stereotype.Service;

@Service
public interface CustomArmorService {

    ArmorApiDto createArmor(ArmorApiDto armorApiDto);

    ArmorApiDto updateArmor(ArmorApiDto armorApiDto);
}
