package fr.ttl.atlasdd.service.character.ogl5.impl;

import fr.ttl.atlasdd.apidto.character.ogl5.CharacterSheetApiDto;
import fr.ttl.atlasdd.apidto.character.ogl5.CharacterSheetCreateRequestApiDto;
import fr.ttl.atlasdd.apidto.character.ogl5.CharacterSheetUpdateRequestApiDto;
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
import fr.ttl.atlasdd.sqldto.character.ogl5.*;
import fr.ttl.atlasdd.sqldto.user.UserSqlDto;
import fr.ttl.atlasdd.utils.character.Alignment;
import fr.ttl.atlasdd.utils.character.CharacterStatus;
import fr.ttl.atlasdd.utils.character.ShieldType;
import fr.ttl.atlasdd.utils.exception.ExceptionMessage;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
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

    public CharacterSheetServiceImpl(
            UserRepo userRepository,
            RaceRepo raceRepository,
            BackgroundRepo backgroundRepository,
            ClassRepo classRepository,
            SkillRepo skillRepository,
            SpellRepo spellRepository,
            WeaponRepo weaponRepository,
            ArmorRepo armorRepository,
            CharacterSheetRepo characterSheetRepository,
            CharacterSheetMapper characterSheetMapper,
            CharacterSheetCreateRequestMapper characterSheetCreateRequestMapper,
            CharacterSheetUpdateRequestMapper characterSheetUpdateRequestMapper
    ) {
        this.userRepository = userRepository;
        this.raceRepository = raceRepository;
        this.backgroundRepository = backgroundRepository;
        this.classRepository = classRepository;
        this.skillRepository = skillRepository;
        this.spellRepository = spellRepository;
        this.weaponRepository = weaponRepository;
        this.armorRepository = armorRepository;
        this.characterSheetRepository = characterSheetRepository;
        this.characterSheetMapper = characterSheetMapper;
        this.characterSheetCreateRequestMapper = characterSheetCreateRequestMapper;
        this.characterSheetUpdateRequestMapper = characterSheetUpdateRequestMapper;
    }

    @Override
    public CharacterSheetApiDto createCharacterSheet(CharacterSheetCreateRequestApiDto request) {
        UserSqlDto user = findUserById(request.getUserId());
        RaceSqlDto race = findRaceById(request.getRaceId());
        BackgroundSqlDto background = findBackgroundById(request.getBackgroundId());
        ClassSqlDto classe = findClassById(request.getClassId());
        List<SkillSqlDto> skills = findSkillsByIds(request.getSkillIds());
        List<SpellSqlDto> spells = findSpellsByIds(request.getPreparedSpellIds());
        List<WeaponSqlDto> weapons = findWeaponsByIds(request.getWeaponIds());
        ArmorSqlDto armor = findArmorById(request.getArmorId());

        CharacterSheetSqlDto characterSheet = new CharacterSheetSqlDto();
        characterSheetCreateRequestMapper.updateSqlDto(request, characterSheet);
        characterSheet.setShield(ShieldType.valueOf(request.getShield()));
        characterSheet.setAlignment(Alignment.valueOf(request.getAlignment()));
        characterSheet.setStatus(CharacterStatus.valueOf(request.getStatus()));
        characterSheet.setOwner(user);
        characterSheet.setRace(race);
        characterSheet.setBackground(background);
        characterSheet.setClasse(classe);
        characterSheet.setSkills(skills);
        characterSheet.setPreparedSpells(spells);
        characterSheet.setWeapons(weapons);
        characterSheet.setArmor(armor);

        try {
            return characterSheetMapper.toApiDto(characterSheetRepository.save(characterSheet));
        } catch (Exception e) {
            throw new Ogl5CharacterSavingErrorException(ExceptionMessage.CHARACTER_SAVE_ERROR.getMessage());
        }
    }

    @Override
    public CharacterSheetApiDto getCharacterSheetById(Long id) {
        CharacterSheetSqlDto characterSheet = characterSheetRepository.findById(id)
                .orElseThrow(() -> new Ogl5CharacterNotFoundException(ExceptionMessage.CHARACTER_NOT_FOUND.getMessage()));

        return characterSheetMapper.toApiDto(characterSheet);
    }

    @Override
    public List<CharacterSheetApiDto> getCharacterSheetsByUserId(Long userId) {
        List<CharacterSheetSqlDto> characterSheets = characterSheetRepository.findAllByOwner_Id(userId);

        return characterSheets.stream().map(characterSheetMapper::toApiDto).collect(Collectors.toList());
    }

    @Override
    public CharacterSheetApiDto updateCharacterSheet(Long id, CharacterSheetUpdateRequestApiDto request) {
        CharacterSheetSqlDto characterSheet = characterSheetRepository.findById(id)
                .orElseThrow(() -> new Ogl5CharacterNotFoundException(ExceptionMessage.CHARACTER_NOT_FOUND.getMessage()));

        List<SkillSqlDto> skills = findSkillsByIds(request.getSkillIds());
        List<SpellSqlDto> spells = findSpellsByIds(request.getPreparedSpellIds());

        characterSheetUpdateRequestMapper.updateSqlDto(request, characterSheet);
        characterSheet.setShield(ShieldType.valueOf(request.getShield()));
        characterSheet.setAlignment(Alignment.valueOf(request.getAlignment()));
        characterSheet.setStatus(CharacterStatus.valueOf(request.getStatus()));
        characterSheet.setSkills(skills);
        characterSheet.setPreparedSpells(spells);
        characterSheet.setUpdatedAt(LocalDateTime.now());

        try {
            return characterSheetMapper.toApiDto(characterSheetRepository.save(characterSheet));
        } catch (Exception e) {
            throw new Ogl5CharacterSavingErrorException(ExceptionMessage.CHARACTER_UPDATE_ERROR.getMessage());
        }
    }


    private UserSqlDto findUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(ExceptionMessage.USER_NOT_FOUND.getMessage()));
    }

    private RaceSqlDto findRaceById(Long raceId) {
        return raceRepository.findById(raceId)
                .orElseThrow(() -> new Ogl5ArmorNotFoundException(ExceptionMessage.ARMOR_NOT_FOUND.getMessage()));
    }

    private BackgroundSqlDto findBackgroundById(Long backgroundId) {
        return backgroundRepository.findById(backgroundId)
                .orElseThrow(() -> new Ogl5BackgroundNotFoundException(ExceptionMessage.BACKGROUND_NOT_FOUND.getMessage()));
    }

    private ClassSqlDto findClassById(Long classId) {
        return classRepository.findById(classId)
                .orElseThrow(() -> new Ogl5ClassNotFoundException(ExceptionMessage.CLASS_NOT_FOUND.getMessage()));
    }

    private List<SkillSqlDto> findSkillsByIds(List<Long> skillIds) {
        try {
            return skillRepository.findAllById(skillIds);
        } catch (Exception e) {
            throw new CharacterSkillNotFoundException(ExceptionMessage.SKILL_RETRIEVE_ERROR.getMessage());
        }
    }

    private List<SpellSqlDto> findSpellsByIds(List<Long> spellIds) {
        try {
            return spellRepository.findAllById(spellIds);
        } catch (Exception e) {
            throw new CharacterPreparedSpellNotFoundException(ExceptionMessage.SPELL_RETRIEVE_ERROR.getMessage());
        }
    }

    private List<WeaponSqlDto> findWeaponsByIds(List<Long> weaponIds) {
        try {
            return weaponRepository.findAllById(weaponIds);
        } catch (Exception e) {
            throw new Ogl5WeaponNotFoundException(ExceptionMessage.WEAPON_RETRIEVE_ERROR.getMessage());
        }
    }

    private ArmorSqlDto findArmorById(Long armorId) {
        return armorRepository.findById(armorId)
                .orElseThrow(() -> new Ogl5ArmorNotFoundException(ExceptionMessage.ARMOR_NOT_FOUND.getMessage()));
    }
}