package fr.ttl.atlasdd.service.impl;

import fr.ttl.atlasdd.apidto.CharacterSheetApiDto;
import fr.ttl.atlasdd.apidto.CharacterSheetRequestApiDto;
import fr.ttl.atlasdd.mapper.CharacterSheetMapper;
import fr.ttl.atlasdd.repository.*;
import fr.ttl.atlasdd.service.CharacterSheetService;
import fr.ttl.atlasdd.sqldto.*;
import fr.ttl.atlasdd.utils.Alignment;
import fr.ttl.atlasdd.utils.CharacterStatus;
import fr.ttl.atlasdd.utils.ShieldType;
import org.springframework.stereotype.Service;

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
            CharacterSheetMapper characterSheetMapper
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
    }

    @Override
    public CharacterSheetApiDto createCharacterSheet(CharacterSheetRequestApiDto request) {
        UserSqlDto user = userRepository.findById(request.getUserId()).orElseThrow();
        RaceSqlDto race = raceRepository.findById(request.getRaceId()).orElseThrow();
        BackgroundSqlDto background = backgroundRepository.findById(request.getBackgroundId()).orElseThrow();
        ClassSqlDto classe = classRepository.findById(request.getClassId()).orElseThrow();
        List<SkillSqlDto> skills = skillRepository.findAllById(request.getSkillIds());
        List<SpellSqlDto> spells = spellRepository.findAllById(request.getPreparedSpellIds());
        List<WeaponSqlDto> weapons = weaponRepository.findAllById(request.getWeaponIds());
        ArmorSqlDto armor = armorRepository.findById(request.getArmorId()).orElseThrow();

        CharacterSheetSqlDto characterSheet = new CharacterSheetSqlDto();
        characterSheet.setName(request.getName());
        characterSheet.setLevel(request.getLevel());
        characterSheet.setExperience(request.getExperience());
        characterSheet.setArmorClass(request.getArmorClass());
        characterSheet.setInitiative(request.getInitiative());
        characterSheet.setInspiration(request.getInspiration());
        characterSheet.setHitPoints(request.getHitPoints());
        characterSheet.setMaxHitPoints(request.getMaxHitPoints());
        characterSheet.setBonusHitPoints(request.getBonusHitPoints());
        characterSheet.setSpeed(request.getSpeed());
        characterSheet.setPassivePerception(request.getPassivePerception());
        characterSheet.setShield(ShieldType.valueOf(request.getShield()));
        characterSheet.setTwoWeaponsFighting(request.isTwoWeaponsFighting());
        characterSheet.setAlignment(Alignment.valueOf(request.getAlignment()));
        characterSheet.setStrength(request.getStrength());
        characterSheet.setDexterity(request.getDexterity());
        characterSheet.setConstitution(request.getConstitution());
        characterSheet.setIntelligence(request.getIntelligence());
        characterSheet.setWisdom(request.getWisdom());
        characterSheet.setCharisma(request.getCharisma());
        characterSheet.setStatus(CharacterStatus.valueOf(request.getStatus()));
        characterSheet.setOwner(user);
        characterSheet.setRace(race);
        characterSheet.setBackground(background);
        characterSheet.setClasse(classe);
        characterSheet.setSkills(skills);
        characterSheet.setPreparedSpells(spells);
        characterSheet.setWeapons(weapons);
        characterSheet.setArmor(armor);

        CharacterSheetSqlDto savedCharacterSheet = characterSheetRepository.save(characterSheet);

        return characterSheetMapper.toApiDto(savedCharacterSheet);
    }

    @Override
    public CharacterSheetApiDto getCharacterSheetById(Long id) {
        CharacterSheetSqlDto characterSheet = characterSheetRepository.findById(id).orElseThrow();

        return characterSheetMapper.toApiDto(characterSheet);
    }

    @Override
    public List<CharacterSheetApiDto> getCharacterSheetsByUserId(Long userId) {
        List<CharacterSheetSqlDto> characterSheets = characterSheetRepository.findAllByOwner_Id(userId);

        return characterSheets.stream().map(characterSheetMapper::toApiDto).collect(Collectors.toList());
    }
}
