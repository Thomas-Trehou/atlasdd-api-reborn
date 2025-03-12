package fr.ttl.atlasdd.service.character;

import fr.ttl.atlasdd.apidto.character.*;
import fr.ttl.atlasdd.apidto.character.ogl5.ClassApiDto;
import fr.ttl.atlasdd.apidto.character.ogl5.RaceApiDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CharacterCreationOptionService {

    List<RaceApiDto> getRaces();

    List<ClassApiDto> getClasses();

    List<BackgroundApiDto> getBackgrounds();

    List<SkillApiDto> getSkills();

    List<SpellApiDto> getSpells();

    List<ArmorApiDto> getArmors();

    List<WeaponApiDto> getWeapons();
}
