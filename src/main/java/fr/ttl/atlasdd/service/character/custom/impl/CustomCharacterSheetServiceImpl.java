package fr.ttl.atlasdd.service.character.custom.impl;

import fr.ttl.atlasdd.apidto.character.custom.CustomCharacterSheetApiDto;
import fr.ttl.atlasdd.apidto.character.custom.CustomCharacterSheetCreateRequestApiDto;
import fr.ttl.atlasdd.apidto.character.custom.CustomCharacterSheetUpdateRequestApiDto;
import fr.ttl.atlasdd.entity.character.custom.CustomCharacterSheet;
import fr.ttl.atlasdd.entity.character.custom.CustomSpell;
import fr.ttl.atlasdd.exception.character.CharacterPreparedSpellNotFoundException;
import fr.ttl.atlasdd.exception.character.CharacterSkillNotFoundException;
import fr.ttl.atlasdd.exception.character.custom.notfound.CustomCharacterNotFoundException;
import fr.ttl.atlasdd.exception.character.custom.savingerror.CustomCharacterSavingErrorException;
import fr.ttl.atlasdd.exception.user.UserNotFoundException;
import fr.ttl.atlasdd.mapper.character.custom.CustomCharacterSheetCreateRequestMapper;
import fr.ttl.atlasdd.mapper.character.custom.CustomCharacterSheetMapper;
import fr.ttl.atlasdd.mapper.character.custom.CustomCharacterSheetUpdateRequestMapper;
import fr.ttl.atlasdd.repository.user.UserRepo;
import fr.ttl.atlasdd.repository.character.custom.CustomCharacterSheetRepo;
import fr.ttl.atlasdd.repository.character.custom.CustomSkillRepo;
import fr.ttl.atlasdd.repository.character.custom.CustomSpellRepo;
import fr.ttl.atlasdd.service.character.custom.*;
import fr.ttl.atlasdd.entity.character.custom.CustomSkill;
import fr.ttl.atlasdd.entity.user.User;
import fr.ttl.atlasdd.utils.exception.ExceptionMessage;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
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

    public CustomCharacterSheetServiceImpl(
            UserRepo userRepository,
            CustomRaceService customRaceService,
            CustomBackgroundService customBackgroundService,
            CustomClassService customClassService,
            CustomSkillRepo customSkillRepository,
            CustomSpellRepo customSpellRepository,
            CustomWeaponService customWeaponService,
            CustomArmorService customArmorService,
            CustomCharacterSheetRepo customCharacterSheetRepository,
            CustomCharacterSheetMapper customCharacterSheetMapper,
            CustomCharacterSheetCreateRequestMapper customCharacterSheetCreateRequestMapper,
            CustomCharacterSheetUpdateRequestMapper customCharacterSheetUpdateRequestMapper
    ) {
        this.userRepository = userRepository;
        this.customRaceService = customRaceService;
        this.customBackgroundService = customBackgroundService;
        this.customClassService = customClassService;
        this.customSkillRepository = customSkillRepository;
        this.customSpellRepository = customSpellRepository;
        this.customWeaponService = customWeaponService;
        this.customArmorService = customArmorService;
        this.customCharacterSheetRepository = customCharacterSheetRepository;
        this.customCharacterSheetMapper = customCharacterSheetMapper;
        this.customCharacterSheetCreateRequestMapper = customCharacterSheetCreateRequestMapper;
        this.customCharacterSheetUpdateRequestMapper = customCharacterSheetUpdateRequestMapper;
    }

    @Override
    public CustomCharacterSheetApiDto createCharacterSheet(CustomCharacterSheetCreateRequestApiDto request) {
        User user = findUserById(request.getUserId());
        List<CustomSkill> skills = findSkillsByIds(request.getSkillIds());
        List<CustomSpell> spells = findSpellsByIds(request.getPreparedSpellIds());

        CustomCharacterSheetApiDto characterSheetApiDto = customCharacterSheetCreateRequestMapper.toApiDto(request);
        characterSheetApiDto.setRace(customRaceService.createRace(characterSheetApiDto.getRace()));
        characterSheetApiDto.setBackground(customBackgroundService.createBackground(characterSheetApiDto.getBackground()));
        characterSheetApiDto.setClasse(customClassService.createClass(characterSheetApiDto.getClasse()));
        characterSheetApiDto.setWeapons(customWeaponService.createWeapons(characterSheetApiDto.getWeapons()));
        characterSheetApiDto.setArmor(customArmorService.createArmor(characterSheetApiDto.getArmor()));

        CustomCharacterSheet characterSheetSqlDto = customCharacterSheetMapper.toSqlDto(characterSheetApiDto);
        characterSheetSqlDto.setOwner(user);
        characterSheetSqlDto.setSkills(skills);
        characterSheetSqlDto.setPreparedSpells(spells);

        try {
            return customCharacterSheetMapper.toApiDto(customCharacterSheetRepository.save(characterSheetSqlDto));
        } catch (Exception e) {
            throw new CustomCharacterSavingErrorException(ExceptionMessage.CHARACTER_SAVE_ERROR.getMessage());
        }
    }

    @Override
    public CustomCharacterSheetApiDto getCharacterSheetById(Long id) {
        CustomCharacterSheet characterSheet = customCharacterSheetRepository.findById(id)
                .orElseThrow(() -> new CustomCharacterNotFoundException(ExceptionMessage.CHARACTER_NOT_FOUND.getMessage()));

        return customCharacterSheetMapper.toApiDto(characterSheet);
    }

    @Override
    public List<CustomCharacterSheetApiDto> getCharacterSheetsByUserId(Long userId) {
        if (!userRepository.existsById(userId)) {
            throw new UserNotFoundException(ExceptionMessage.USER_NOT_FOUND.getMessage());
        }

        List<CustomCharacterSheet> characterSheets = customCharacterSheetRepository.findAllByOwner_Id(userId);

        return characterSheets.stream().map(customCharacterSheetMapper::toApiDto).collect(Collectors.toList());
    }

    @Override
    public CustomCharacterSheetApiDto updateCharacterSheet(Long id, CustomCharacterSheetUpdateRequestApiDto request) {
        CustomCharacterSheet characterSheet = customCharacterSheetRepository.findById(id)
                .orElseThrow(() -> new CustomCharacterNotFoundException(ExceptionMessage.CHARACTER_NOT_FOUND.getMessage()));

        List<CustomSkill> skills = findSkillsByIds(request.getSkillIds());
        List<CustomSpell> spells = findSpellsByIds(request.getPreparedSpellIds());

        CustomCharacterSheetApiDto characterSheetApiDto = customCharacterSheetUpdateRequestMapper.toApiDto(request);
        characterSheetApiDto.setRace(customRaceService.updateRace(characterSheetApiDto.getRace()));
        characterSheetApiDto.setBackground(customBackgroundService.updateBackground(characterSheetApiDto.getBackground()));
        characterSheetApiDto.setClasse(customClassService.updateClass(characterSheetApiDto.getClasse()));
        characterSheetApiDto.setWeapons(customWeaponService.updateWeapons(characterSheetApiDto.getWeapons()));
        characterSheetApiDto.setArmor(customArmorService.updateArmor(characterSheetApiDto.getArmor()));

        customCharacterSheetMapper.updateSqlDto(characterSheetApiDto, characterSheet);
        characterSheet.setSkills(skills);
        characterSheet.setPreparedSpells(spells);

        try {
            return customCharacterSheetMapper.toApiDto(customCharacterSheetRepository.save(characterSheet));
        } catch (Exception e) {
            throw new CustomCharacterSavingErrorException(ExceptionMessage.CHARACTER_UPDATE_ERROR.getMessage());
        }
    }

    @Override
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

    private List<CustomSkill> findSkillsByIds(List<Long> skillIds) {
        try {
            return customSkillRepository.findAllById(skillIds);
        } catch (Exception e) {
            throw new CharacterSkillNotFoundException(ExceptionMessage.SKILL_RETRIEVE_ERROR.getMessage());
        }
    }

    private List<CustomSpell> findSpellsByIds(List<Long> spellIds) {
        try {
            return customSpellRepository.findAllById(spellIds);
        } catch (Exception e) {
            throw new CharacterPreparedSpellNotFoundException(ExceptionMessage.SPELL_RETRIEVE_ERROR.getMessage());
        }
    }
}