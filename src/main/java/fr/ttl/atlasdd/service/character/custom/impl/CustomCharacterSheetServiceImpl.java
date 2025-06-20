package fr.ttl.atlasdd.service.character.custom.impl;

import fr.ttl.atlasdd.apidto.character.CharacterSkillDto;
import fr.ttl.atlasdd.apidto.character.custom.CustomCharacterSheetApiDto;
import fr.ttl.atlasdd.apidto.character.custom.CustomCharacterSheetCreateRequestApiDto;
import fr.ttl.atlasdd.apidto.character.custom.CustomCharacterSheetUpdateRequestApiDto;
import fr.ttl.atlasdd.entity.character.custom.CustomCharacterSheet;
import fr.ttl.atlasdd.entity.character.custom.CustomCharacterSkill;
import fr.ttl.atlasdd.entity.character.custom.CustomSpell;
import fr.ttl.atlasdd.exception.character.CharacterPreparedSpellNotFoundException;
import fr.ttl.atlasdd.exception.character.CharacterSkillNotFoundException;
import fr.ttl.atlasdd.exception.character.custom.notfound.CustomCharacterNotFoundException;
import fr.ttl.atlasdd.exception.character.custom.savingerror.CustomCharacterSavingErrorException;
import fr.ttl.atlasdd.exception.user.UserNotFoundException;
import fr.ttl.atlasdd.mapper.character.custom.CustomCharacterSheetCreateRequestMapper;
import fr.ttl.atlasdd.mapper.character.custom.CustomCharacterSheetMapper;
import fr.ttl.atlasdd.mapper.character.custom.CustomCharacterSheetUpdateRequestMapper;
import fr.ttl.atlasdd.repository.character.custom.CustomCharacterSkillRepo;
import fr.ttl.atlasdd.repository.user.UserRepo;
import fr.ttl.atlasdd.repository.character.custom.CustomCharacterSheetRepo;
import fr.ttl.atlasdd.repository.character.custom.CustomSkillRepo;
import fr.ttl.atlasdd.repository.character.custom.CustomSpellRepo;
import fr.ttl.atlasdd.service.character.custom.*;
import fr.ttl.atlasdd.entity.character.custom.CustomSkill;
import fr.ttl.atlasdd.entity.user.User;
import fr.ttl.atlasdd.utils.exception.ExceptionMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomCharacterSheetServiceImpl implements CustomCharacterSheetService {

    private final UserRepo userRepository;
    private final CustomRaceService customRaceService;
    private final CustomBackgroundService customBackgroundService;
    private final CustomClassService customClassService;
    private final CustomSkillRepo customSkillRepository;
    private final CustomSpellRepo customSpellRepository;
    private final CustomWeaponService customWeaponService;
    private final CustomArmorService customArmorService;
    private final CustomCharacterSheetRepo customCharacterSheetRepository;
    private final CustomCharacterSheetMapper customCharacterSheetMapper;
    private final CustomCharacterSheetCreateRequestMapper customCharacterSheetCreateRequestMapper;
    private final CustomCharacterSheetUpdateRequestMapper customCharacterSheetUpdateRequestMapper;
    private final CustomCharacterSkillRepo customCharacterSkillRepository;

    @Override
    @Transactional
    public CustomCharacterSheetApiDto createCharacterSheet(CustomCharacterSheetCreateRequestApiDto request) {
        User user = findUserById(request.getUserId());

        List<CustomSpell> spells = findSpellsByIds(request.getPreparedSpellIds());

        CustomCharacterSheetApiDto characterSheetApiDto = customCharacterSheetCreateRequestMapper.toApiDto(request);
        characterSheetApiDto.setRace(customRaceService.createRace(characterSheetApiDto.getRace()));
        characterSheetApiDto.setBackground(customBackgroundService.createBackground(characterSheetApiDto.getBackground()));
        characterSheetApiDto.setClasse(customClassService.createClass(characterSheetApiDto.getClasse()));
        characterSheetApiDto.setWeapons(customWeaponService.createWeapons(characterSheetApiDto.getWeapons()));
        characterSheetApiDto.setArmor(customArmorService.createArmor(characterSheetApiDto.getArmor()));

        CustomCharacterSheet characterSheetSqlDto = customCharacterSheetMapper.toEntity(characterSheetApiDto);
        characterSheetSqlDto.setOwner(user);
        characterSheetSqlDto.setPreparedSpells(spells);

        try {
           CustomCharacterSheet savedCharacterSheet = customCharacterSheetRepository.save(characterSheetSqlDto);
           updateCharacterSkills(savedCharacterSheet, request.getSkills());
           return customCharacterSheetMapper.toApiDto(savedCharacterSheet);
        } catch (Exception e) {
            throw new CustomCharacterSavingErrorException(ExceptionMessage.CHARACTER_SAVE_ERROR.getMessage());
        }
    }

    @Override
    @Transactional(readOnly = true)
    public CustomCharacterSheetApiDto getCharacterSheetById(Long id) {
        CustomCharacterSheet characterSheet = customCharacterSheetRepository.findByIdWithSkills(id)
                .orElseThrow(() -> new CustomCharacterNotFoundException(ExceptionMessage.CHARACTER_NOT_FOUND.getMessage()));

        return customCharacterSheetMapper.toApiDto(characterSheet);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CustomCharacterSheetApiDto> getCharacterSheetsByUserId(Long userId) {
        if (!userRepository.existsById(userId)) {
            throw new UserNotFoundException(ExceptionMessage.USER_NOT_FOUND.getMessage());
        }

        List<CustomCharacterSheet> characterSheets = customCharacterSheetRepository.findAllByOwner_Id(userId);

        return characterSheets.stream().map(customCharacterSheetMapper::toApiDto).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public CustomCharacterSheetApiDto updateCharacterSheet(Long id, CustomCharacterSheetUpdateRequestApiDto request) {
        CustomCharacterSheet characterSheet = customCharacterSheetRepository.findById(id)
                .orElseThrow(() -> new CustomCharacterNotFoundException(ExceptionMessage.CHARACTER_NOT_FOUND.getMessage()));

        List<CustomSpell> spells = findSpellsByIds(request.getPreparedSpellIds());

        CustomCharacterSheetApiDto characterSheetApiDto = customCharacterSheetUpdateRequestMapper.toApiDto(request);
        characterSheetApiDto.setRace(customRaceService.updateRace(characterSheetApiDto.getRace()));
        characterSheetApiDto.setBackground(customBackgroundService.updateBackground(characterSheetApiDto.getBackground()));
        characterSheetApiDto.setClasse(customClassService.updateClass(characterSheetApiDto.getClasse()));
        characterSheetApiDto.setWeapons(customWeaponService.updateWeapons(characterSheetApiDto.getWeapons()));
        characterSheetApiDto.setArmor(customArmorService.updateArmor(characterSheetApiDto.getArmor()));

        customCharacterSheetMapper.updateSqlDto(characterSheetApiDto, characterSheet);
        characterSheet.setPreparedSpells(spells);

        try {
            CustomCharacterSheet savedCharacterSheet = customCharacterSheetRepository.save(characterSheet);
            updateCharacterSkills(savedCharacterSheet, request.getSkills());
            return customCharacterSheetMapper.toApiDto(savedCharacterSheet);

        } catch (Exception e) {
            throw new CustomCharacterSavingErrorException(ExceptionMessage.CHARACTER_UPDATE_ERROR.getMessage());
        }
    }

    @Override
    @Transactional
    public void deleteCharacterSheet(Long id) {
        CustomCharacterSheet characterSheet = customCharacterSheetRepository.findById(id)
                .orElseThrow(() -> new CustomCharacterNotFoundException(ExceptionMessage.CHARACTER_NOT_FOUND.getMessage()));

        try {
            customCharacterSheetRepository.delete(characterSheet);
        } catch (Exception e) {
            throw new CustomCharacterSavingErrorException(ExceptionMessage.CHARACTER_DELETE_ERROR.getMessage());
        }
    }

    private User findUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(ExceptionMessage.USER_NOT_FOUND.getMessage()));
    }

    private List<CustomSpell> findSpellsByIds(List<Long> spellIds) {
        try {
            return customSpellRepository.findAllById(spellIds);
        } catch (Exception e) {
            throw new CharacterPreparedSpellNotFoundException(ExceptionMessage.SPELL_RETRIEVE_ERROR.getMessage());
        }
    }

    private void updateCharacterSkills(CustomCharacterSheet characterSheet, List<CharacterSkillDto> skillDtos) {
        customCharacterSkillRepository.deleteByCharacterSheetId(characterSheet.getId());

        List<CustomCharacterSkill> characterSkills = skillDtos.stream()
                .map(skillDto -> {
                    CustomSkill skill = findSkillById(skillDto.getId());
                    CustomCharacterSkill characterSkill = new CustomCharacterSkill();
                    characterSkill.setCharacterSheet(characterSheet);
                    characterSkill.setSkill(skill);
                    characterSkill.setExpert(skillDto.isExpert());
                    return characterSkill;
                })
                .collect(Collectors.toList());

        customCharacterSkillRepository.saveAll(characterSkills);
        characterSheet.setCharacterSkills(characterSkills);
    }

    private CustomSkill findSkillById(Long skillId) {
        return customSkillRepository.findById(skillId)
                .orElseThrow(() -> new CharacterSkillNotFoundException(ExceptionMessage.SKILL_RETRIEVE_ERROR.getMessage()));
    }
}