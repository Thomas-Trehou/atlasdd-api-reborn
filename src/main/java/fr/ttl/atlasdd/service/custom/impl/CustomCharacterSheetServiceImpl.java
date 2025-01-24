package fr.ttl.atlasdd.service.custom.impl;

import fr.ttl.atlasdd.apidto.custom.CustomCharacterSheetApiDto;
import fr.ttl.atlasdd.apidto.custom.CustomCharacterSheetCreateRequestApiDto;
import fr.ttl.atlasdd.apidto.custom.CustomCharacterSheetUpdateRequestApiDto;
import fr.ttl.atlasdd.mapper.custom.CustomCharacterSheetCreateRequestMapper;
import fr.ttl.atlasdd.mapper.custom.CustomCharacterSheetMapper;
import fr.ttl.atlasdd.mapper.custom.CustomCharacterSheetUpdateRequestMapper;
import fr.ttl.atlasdd.repository.UserRepo;
import fr.ttl.atlasdd.repository.custom.*;
import fr.ttl.atlasdd.service.custom.CustomCharacterSheetService;
import fr.ttl.atlasdd.sqldto.NoteCharacterSqlDto;
import fr.ttl.atlasdd.sqldto.UserSqlDto;
import fr.ttl.atlasdd.sqldto.custom.CustomCharacterSheetSqlDto;
import fr.ttl.atlasdd.sqldto.custom.CustomSkillSqlDto;
import fr.ttl.atlasdd.sqldto.custom.CustomSpellSqlDto;
import fr.ttl.atlasdd.sqldto.ogl5.SkillSqlDto;
import fr.ttl.atlasdd.sqldto.ogl5.SpellSqlDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomCharacterSheetServiceImpl implements CustomCharacterSheetService {

    private final UserRepo userRepository;
    private final CustomRaceRepo customRaceRepository;
    private final CustomBackgroundRepo customBackgroundRepository;
    private final CustomClassRepo customClassRepository;
    private final CustomSkillRepo customSkillRepository;
    private final CustomSpellRepo customSpellRepository;
    private final CustomWeaponRepo customWeaponRepository;
    private final CustomArmorRepo customArmorRepository;
    private final CustomCharacterSheetRepo customCharacterSheetRepository;
    private final CustomCharacterSheetMapper customCharacterSheetMapper;
    private final CustomCharacterSheetCreateRequestMapper customCharacterSheetCreateRequestMapper;
    private final CustomCharacterSheetUpdateRequestMapper customCharacterSheetUpdateRequestMapper;

    public CustomCharacterSheetServiceImpl(
            UserRepo userRepository,
            CustomRaceRepo customRaceRepository,
            CustomBackgroundRepo customBackgroundRepository,
            CustomClassRepo customClassRepository,
            CustomSkillRepo customSkillRepository,
            CustomSpellRepo customSpellRepository,
            CustomWeaponRepo customWeaponRepository,
            CustomArmorRepo customArmorRepository,
            CustomCharacterSheetRepo customCharacterSheetRepository,
            CustomCharacterSheetMapper customCharacterSheetMapper,
            CustomCharacterSheetCreateRequestMapper customCharacterSheetCreateRequestMapper,
            CustomCharacterSheetUpdateRequestMapper customCharacterSheetUpdateRequestMapper
    ) {
        this.userRepository = userRepository;
        this.customRaceRepository = customRaceRepository;
        this.customBackgroundRepository = customBackgroundRepository;
        this.customClassRepository = customClassRepository;
        this.customSkillRepository = customSkillRepository;
        this.customSpellRepository = customSpellRepository;
        this.customWeaponRepository = customWeaponRepository;
        this.customArmorRepository = customArmorRepository;
        this.customCharacterSheetRepository = customCharacterSheetRepository;
        this.customCharacterSheetMapper = customCharacterSheetMapper;
        this.customCharacterSheetCreateRequestMapper = customCharacterSheetCreateRequestMapper;
        this.customCharacterSheetUpdateRequestMapper = customCharacterSheetUpdateRequestMapper;
    }

    @Override
    public CustomCharacterSheetApiDto createCharacterSheet(CustomCharacterSheetCreateRequestApiDto characterSheetCreateRequestApiDto) {

        UserSqlDto userSqlDto = userRepository.findById(characterSheetCreateRequestApiDto.getOwner().getId()).orElseThrow();
        List<CustomSkillSqlDto> skills = customSkillRepository.findAllById(characterSheetCreateRequestApiDto.getSkillIds());
        List<CustomSpellSqlDto> spells = customSpellRepository.findAllById(characterSheetCreateRequestApiDto.getPreparedSpellIds());
        List<NoteCharacterSqlDto> notes = new ArrayList<>();

        CustomCharacterSheetApiDto characterSheetApiDto = customCharacterSheetCreateRequestMapper.toApiDto(characterSheetCreateRequestApiDto);

        CustomCharacterSheetSqlDto characterSheetSqlDto = customCharacterSheetMapper.toSqlDto(characterSheetApiDto);
        characterSheetSqlDto.setOwner(userSqlDto);
        characterSheetSqlDto.setSkills(skills);
        characterSheetSqlDto.setPreparedSpells(spells);
        characterSheetSqlDto.setCharacterNotes(notes);

        CustomCharacterSheetSqlDto savedCharacterSheetSqlDto = customCharacterSheetRepository.save(characterSheetSqlDto);

        return customCharacterSheetMapper.toApiDto(savedCharacterSheetSqlDto);
    }

    @Override
    public CustomCharacterSheetApiDto getCharacterSheetById(Long id) {

        CustomCharacterSheetSqlDto characterSheetSqlDto = customCharacterSheetRepository.findById(id).orElse(null);

        return customCharacterSheetMapper.toApiDto(characterSheetSqlDto);
    }

    @Override
    public List<CustomCharacterSheetApiDto> getCharacterSheetsByUserId(Long userId) {

        List<CustomCharacterSheetSqlDto> characterSheets = customCharacterSheetRepository.findAllByOwner_Id(userId);

        return characterSheets.stream().map(customCharacterSheetMapper::toApiDto).toList();
    }

    @Override
    public CustomCharacterSheetApiDto updateCharacterSheet(Long id, CustomCharacterSheetUpdateRequestApiDto customCharacterSheetUpdateRequestApiDto) {

        CustomCharacterSheetSqlDto characterSheetSqlDto = customCharacterSheetRepository.findById(id).orElseThrow();

        List<CustomSkillSqlDto> skills = customSkillRepository.findAllById(customCharacterSheetUpdateRequestApiDto.getSkillIds());
        List<CustomSpellSqlDto> spells = customSpellRepository.findAllById(customCharacterSheetUpdateRequestApiDto.getPreparedSpellIds());

        CustomCharacterSheetApiDto characterSheetApiDto = customCharacterSheetUpdateRequestMapper.toApiDto(customCharacterSheetUpdateRequestApiDto);

        customCharacterSheetMapper.updateSqlDto(characterSheetApiDto, characterSheetSqlDto);
        characterSheetSqlDto.setSkills(skills);
        characterSheetSqlDto.setPreparedSpells(spells);

        CustomCharacterSheetSqlDto updatedCharacterSheetSqlDto = customCharacterSheetRepository.save(characterSheetSqlDto);

        return customCharacterSheetMapper.toApiDto(updatedCharacterSheetSqlDto);
    }

}
