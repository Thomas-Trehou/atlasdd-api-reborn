package fr.ttl.atlasdd.mapper.character.custom;

import fr.ttl.atlasdd.apidto.character.custom.CustomCharacterSheetApiDto;
import fr.ttl.atlasdd.entity.character.custom.CustomCharacterSheet;
import fr.ttl.atlasdd.mapper.character.ogl5.CharacterSkillMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        uses = {CharacterSkillMapper.class})
public interface CustomCharacterSheetMapper {

    @Mapping(target = "skills", source = "characterSkills")
    CustomCharacterSheetApiDto toApiDto(CustomCharacterSheet characterSheetSqlDto);

    @Mapping(target = "characterNotes", ignore = true)
    @Mapping(target = "campaigns", ignore = true)
    @Mapping(target = "characterSkills", source = "skills")
    CustomCharacterSheet toEntity(CustomCharacterSheetApiDto characterSheetApiDto);

    @Mapping(target = "characterNotes", ignore = true)
    @Mapping(target = "campaigns", ignore = true)
    @Mapping(target = "characterSkills", ignore = true)
    @Mapping(target = "owner", ignore = true)
    void updateSqlDto(CustomCharacterSheetApiDto characterSheetApiDto, @MappingTarget CustomCharacterSheet characterSheetSqlDto);
}
