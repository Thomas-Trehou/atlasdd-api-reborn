package fr.ttl.atlasdd.service.custom;

import fr.ttl.atlasdd.apidto.custom.CustomArmorApiDto;
import org.springframework.stereotype.Service;

@Service
public interface CustomArmorService {

    CustomArmorApiDto createArmor(CustomArmorApiDto armorApiDto);

    CustomArmorApiDto updateArmor(CustomArmorApiDto armorApiDto);
}
