package fr.ttl.atlasdd.service.character.impl;

import fr.ttl.atlasdd.apidto.character.*;
import fr.ttl.atlasdd.apidto.character.ogl5.ClassApiDto;
import fr.ttl.atlasdd.apidto.character.ogl5.RaceApiDto;
import fr.ttl.atlasdd.exception.character.OptionNotFoundException;
import fr.ttl.atlasdd.mapper.character.ogl5.*;
import fr.ttl.atlasdd.repository.character.ogl5.*;
import fr.ttl.atlasdd.service.character.CharacterCreationOptionService;
import fr.ttl.atlasdd.utils.exception.ExceptionMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CharacterCreationOptionServiceImpl implements CharacterCreationOptionService {

    private final RaceRepo raceRepo;
    private final ClassRepo classRepo;
    private final BackgroundRepo backgroundRepo;
    private final SkillRepo skillRepo;
    private final SpellRepo spellRepo;
    private final ArmorRepo armorRepo;
    private final WeaponRepo weaponRepo;
    private final RaceMapper raceMapper;
    private final ClassMapper classMapper;
    private final BackgroundMapper backgroundMapper;
    private final SkillMapper skillMapper;
    private final SpellMapper spellMapper;
    private final ArmorMapper armorMapper;
    private final WeaponMapper weaponMapper;

    @Override
    public List<RaceApiDto> getRaces(){
        try {
            return raceRepo.findAll().stream()
                    .map(raceMapper::toApiDto)
                    .toList();
        } catch (Exception e) {
            throw new OptionNotFoundException(ExceptionMessage.RACE_RETRIEVE_ERROR.getMessage());
        }
    }

    @Override
    public List<ClassApiDto> getClasses(){
        try {
            return classRepo.findAll().stream()
                    .map(classMapper::toApiDto)
                    .toList();
        } catch (Exception e) {
            throw new OptionNotFoundException(ExceptionMessage.CLASS_RETRIEVE_ERROR.getMessage());
        }
    }

    @Override
    public List<BackgroundApiDto> getBackgrounds(){
        try {
            return backgroundRepo.findAll().stream()
                    .map(backgroundMapper::toApiDto)
                    .toList();
        } catch (Exception e) {
            throw new OptionNotFoundException(ExceptionMessage.BACKGROUND_RETRIEVE_ERROR.getMessage());
        }
    }

    @Override
    public List<SkillApiDto> getSkills(){
        try {
            return skillRepo.findAll().stream()
                    .map(skillMapper::toApiDto)
                    .toList();
        } catch (Exception e) {
            throw new OptionNotFoundException(ExceptionMessage.SKILL_RETRIEVE_ERROR.getMessage());
        }
    }

    @Override
    public List<SpellApiDto> getSpells(){
        try {
            return spellRepo.findAll().stream()
                    .map(spellMapper::toApiDto)
                    .toList();
        } catch (Exception e) {
            throw new OptionNotFoundException(ExceptionMessage.SPELL_RETRIEVE_ERROR.getMessage());
        }
    }

    @Override
    public List<ArmorApiDto> getArmors(){
        try {
            return armorRepo.findAll().stream()
                    .map(armorMapper::toApiDto)
                    .toList();
        } catch (Exception e) {
            throw new OptionNotFoundException(ExceptionMessage.ARMOR_RETRIEVE_ERROR.getMessage());
        }
    }

    @Override
    public List<WeaponApiDto> getWeapons(){
        try {
            return weaponRepo.findAll().stream()
                    .map(weaponMapper::toApiDto)
                    .toList();
        } catch (Exception e) {
            throw new OptionNotFoundException(ExceptionMessage.WEAPON_RETRIEVE_ERROR.getMessage());
        }
    }


}
