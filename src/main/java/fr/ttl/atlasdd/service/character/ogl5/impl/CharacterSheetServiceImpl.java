package fr.ttl.atlasdd.service.character.ogl5.impl;

import fr.ttl.atlasdd.apidto.character.ogl5.CharacterSheetApiDto;
import fr.ttl.atlasdd.apidto.character.ogl5.CharacterSheetCreateRequestApiDto;
import fr.ttl.atlasdd.apidto.character.ogl5.CharacterSheetUpdateRequestApiDto;
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
        UserSqlDto user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new UserNotFoundException("Utilisateur non trouvé", 404));
        RaceSqlDto race = raceRepository.findById(request.getRaceId()).orElseThrow();
        BackgroundSqlDto background = backgroundRepository.findById(request.getBackgroundId()).orElseThrow();
        ClassSqlDto classe = classRepository.findById(request.getClassId()).orElseThrow();
        List<SkillSqlDto> skills = skillRepository.findAllById(request.getSkillIds());
        List<SpellSqlDto> spells = spellRepository.findAllById(request.getPreparedSpellIds());
        List<WeaponSqlDto> weapons = weaponRepository.findAllById(request.getWeaponIds());
        ArmorSqlDto armor = armorRepository.findById(request.getArmorId()).orElseThrow();

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

    @Override
    public CharacterSheetApiDto updateCharacterSheet(Long id, CharacterSheetUpdateRequestApiDto request) {
        CharacterSheetSqlDto characterSheet = characterSheetRepository.findById(id).orElseThrow();

        List<SkillSqlDto> skills = skillRepository.findAllById(request.getSkillIds());
        List<SpellSqlDto> spells = spellRepository.findAllById(request.getPreparedSpellIds());

        characterSheetUpdateRequestMapper.updateSqlDto(request, characterSheet);
        characterSheet.setShield(ShieldType.valueOf(request.getShield()));
        characterSheet.setAlignment(Alignment.valueOf(request.getAlignment()));
        characterSheet.setStatus(CharacterStatus.valueOf(request.getStatus()));
        characterSheet.setSkills(skills);
        characterSheet.setPreparedSpells(spells);
        characterSheet.setUpdatedAt(LocalDateTime.now());

        CharacterSheetSqlDto savedCharacterSheet = characterSheetRepository.save(characterSheet);

        return characterSheetMapper.toApiDto(savedCharacterSheet);
    }
}
