package fr.ttl.atlasdd.service.character.ogl5.impl;

import fr.ttl.atlasdd.apidto.character.CharacterSkillDto;
import fr.ttl.atlasdd.apidto.character.ogl5.CharacterSheetApiDto;
import fr.ttl.atlasdd.apidto.character.ogl5.CharacterSheetCreateRequestApiDto;
import fr.ttl.atlasdd.apidto.character.ogl5.CharacterSheetUpdateRequestApiDto;
import fr.ttl.atlasdd.entity.user.User;
import fr.ttl.atlasdd.exception.character.CharacterPreparedSpellNotFoundException;
import fr.ttl.atlasdd.exception.character.CharacterSkillNotFoundException;
import fr.ttl.atlasdd.exception.character.ogl5.notfound.*;
import fr.ttl.atlasdd.exception.character.ogl5.savingerror.Ogl5CharacterSavingErrorException;
import fr.ttl.atlasdd.exception.user.UserNotFoundException;
import fr.ttl.atlasdd.mapper.character.ogl5.CharacterSheetCreateRequestMapper;
import fr.ttl.atlasdd.mapper.character.ogl5.CharacterSheetMapper;
import fr.ttl.atlasdd.mapper.character.ogl5.CharacterSheetUpdateRequestMapper;
import fr.ttl.atlasdd.repository.character.ogl5.*;
import fr.ttl.atlasdd.repository.user.UserRepo;
import fr.ttl.atlasdd.service.character.ogl5.CharacterSheetService;
import fr.ttl.atlasdd.entity.character.ogl5.*;
import fr.ttl.atlasdd.utils.exception.ExceptionMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CharacterSheetServiceImpl implements CharacterSheetService {

    private final UserRepo userRepository;
    private final RaceRepo raceRepository;
    private final BackgroundRepo backgroundRepository;
    private final ClassRepo classRepository;
    private final SkillRepo skillRepository;
    private final SpellRepo spellRepository;
    private final WeaponRepo weaponRepository;
    private final ArmorRepo armorRepository;
    private final CharacterSheetRepo characterSheetRepository;
    private final CharacterSheetMapper characterSheetMapper;
    private final CharacterSheetCreateRequestMapper characterSheetCreateRequestMapper;
    private final CharacterSheetUpdateRequestMapper characterSheetUpdateRequestMapper;
    private final CharacterSkillRepo characterSkillRepository;

    @Override
    @Transactional
    public CharacterSheetApiDto createCharacterSheet(CharacterSheetCreateRequestApiDto request) {
        User user = findUserById(request.getUserId());
        Ogl5Race race = findRaceById(request.getRaceId());
        Ogl5Background background = findBackgroundById(request.getBackgroundId());
        Ogl5Class classe = findClassById(request.getClassId());
        List<Ogl5Spell> spells = findSpellsByIds(request.getPreparedSpellIds());
        List<Ogl5Weapon> weapons = findWeaponsByIds(request.getWeaponIds());
        Ogl5Armor armor = findArmorById(request.getArmorId());

        Ogl5CharacterSheet characterSheet = new Ogl5CharacterSheet();
        characterSheetCreateRequestMapper.updateSqlDto(request, characterSheet);
        characterSheet.setShield(request.getShield());
        characterSheet.setAlignment(request.getAlignment());
        characterSheet.setStatus(request.getStatus());
        characterSheet.setOwner(user);
        characterSheet.setRace(race);
        characterSheet.setBackground(background);
        characterSheet.setClasse(classe);
        characterSheet.setPreparedSpells(spells);
        characterSheet.setWeapons(weapons);
        characterSheet.setArmor(armor);

        try {
            Ogl5CharacterSheet savedCharacterSheet = characterSheetRepository.save(characterSheet);
            updateCharacterSkills(savedCharacterSheet, request.getSkills());
            return characterSheetMapper.toApiDto(savedCharacterSheet);

        } catch (Exception e) {
            throw new Ogl5CharacterSavingErrorException(ExceptionMessage.CHARACTER_SAVE_ERROR.getMessage());
        }
    }

    @Override
    @Transactional(readOnly = true)
    public CharacterSheetApiDto getCharacterSheetById(Long id) {
        Ogl5CharacterSheet characterSheet = characterSheetRepository.findByIdWithSkills(id)
                .orElseThrow(() -> new Ogl5CharacterNotFoundException(ExceptionMessage.CHARACTER_NOT_FOUND.getMessage()));

        return characterSheetMapper.toApiDto(characterSheet);
    }


    @Override
    @Transactional(readOnly = true)
    public List<CharacterSheetApiDto> getCharacterSheetsByUserId(Long userId) {
        List<Ogl5CharacterSheet> characterSheets = characterSheetRepository.findAllByOwner_Id(userId);

        return characterSheets.stream().map(characterSheetMapper::toApiDto).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public CharacterSheetApiDto updateCharacterSheet(Long id, CharacterSheetUpdateRequestApiDto request) {
        Ogl5CharacterSheet characterSheet = characterSheetRepository.findById(id)
                .orElseThrow(() -> new Ogl5CharacterNotFoundException(ExceptionMessage.CHARACTER_NOT_FOUND.getMessage()));

        List<Ogl5Spell> spells = findSpellsByIds(request.getPreparedSpellIds());
        List<Ogl5Weapon> weapons = findWeaponsByIds(request.getWeaponIds());
        Ogl5Armor armor = findArmorById(request.getArmorId());

        characterSheetUpdateRequestMapper.updateSqlDto(request, characterSheet);
        characterSheet.setShield(request.getShield());
        characterSheet.setAlignment(request.getAlignment());
        characterSheet.setStatus(request.getStatus());
        characterSheet.setPreparedSpells(spells);
        characterSheet.setUpdatedAt(LocalDateTime.now());
        characterSheet.setWeapons(weapons);
        characterSheet.setArmor(armor);

        try {
            Ogl5CharacterSheet savedCharacterSheet = characterSheetRepository.save(characterSheet);
            updateCharacterSkills(savedCharacterSheet, request.getSkills());
            return characterSheetMapper.toApiDto(savedCharacterSheet);

        } catch (Exception e) {
            throw new Ogl5CharacterSavingErrorException(ExceptionMessage.CHARACTER_UPDATE_ERROR.getMessage());
        }
    }

    @Override
    @Transactional
    public void deleteCharacterSheet(Long id) {
        Ogl5CharacterSheet characterSheet = characterSheetRepository.findById(id)
                .orElseThrow(() -> new Ogl5CharacterNotFoundException(ExceptionMessage.CHARACTER_NOT_FOUND.getMessage()));

        characterSheetRepository.delete(characterSheet);

        if(characterSheetRepository.existsById(id)) {
            throw new Ogl5CharacterSavingErrorException(ExceptionMessage.CHARACTER_DELETE_ERROR.getMessage());
        }
    }


    private User findUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(ExceptionMessage.USER_NOT_FOUND.getMessage()));
    }

    private Ogl5Race findRaceById(Long raceId) {
        return raceRepository.findById(raceId)
                .orElseThrow(() -> new Ogl5RaceNotFoundException(ExceptionMessage.RACE_NOT_FOUND.getMessage()));
    }

    private Ogl5Background findBackgroundById(Long backgroundId) {
        return backgroundRepository.findById(backgroundId)
                .orElseThrow(() -> new Ogl5BackgroundNotFoundException(ExceptionMessage.BACKGROUND_NOT_FOUND.getMessage()));
    }

    private Ogl5Class findClassById(Long classId) {
        return classRepository.findById(classId)
                .orElseThrow(() -> new Ogl5ClassNotFoundException(ExceptionMessage.CLASS_NOT_FOUND.getMessage()));
    }

    private List<Ogl5Spell> findSpellsByIds(List<Long> spellIds) {
        try {
            return spellRepository.findAllById(spellIds);
        } catch (Exception e) {
            throw new CharacterPreparedSpellNotFoundException(ExceptionMessage.SPELL_RETRIEVE_ERROR.getMessage());
        }
    }

    private List<Ogl5Weapon> findWeaponsByIds(List<Long> weaponIds) {
        try {
            return weaponRepository.findAllById(weaponIds);
        } catch (Exception e) {
            throw new Ogl5WeaponNotFoundException(ExceptionMessage.WEAPON_RETRIEVE_ERROR.getMessage());
        }
    }

    private Ogl5Armor findArmorById(Long armorId) {
        return armorRepository.findById(armorId)
                .orElseThrow(() -> new Ogl5ArmorNotFoundException(ExceptionMessage.ARMOR_NOT_FOUND.getMessage()));
    }

    private void updateCharacterSkills(Ogl5CharacterSheet characterSheet, List<CharacterSkillDto> skillDtos) {
        characterSkillRepository.deleteByCharacterSheetId(characterSheet.getId());

        List<Ogl5CharacterSkill> characterSkills = skillDtos.stream()
                .map(skillDto -> {
                    Ogl5Skill skill = findSkillById(skillDto.getId());
                    Ogl5CharacterSkill characterSkill = new Ogl5CharacterSkill();
                    characterSkill.setCharacterSheet(characterSheet);
                    characterSkill.setSkill(skill);
                    characterSkill.setExpert(skillDto.isExpert());
                    return characterSkill;
                })
                .collect(Collectors.toList());

        characterSkillRepository.saveAll(characterSkills);
        characterSheet.setCharacterSkills(characterSkills);
    }

    private Ogl5Skill findSkillById(Long skillId) {
        return skillRepository.findById(skillId)
                .orElseThrow(() -> new CharacterSkillNotFoundException(ExceptionMessage.SKILL_RETRIEVE_ERROR.getMessage()));
    }

}