package fr.ttl.atlasdd.mapper.character.ogl5;

import fr.ttl.atlasdd.apidto.character.ogl5.CharacterSheetApiDto;
import fr.ttl.atlasdd.entity.character.ogl5.Ogl5CharacterSheet;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        uses = {CharacterSkillMapper.class})
public interface CharacterSheetMapper {

    @Mapping(target = "skills", source = "characterSkills")
    CharacterSheetApiDto toApiDto(Ogl5CharacterSheet ogl5CharacterSheet);

    @Mapping(target = "characterSkills", source = "skills")
    Ogl5CharacterSheet toEntity(CharacterSheetApiDto characterSheetApiDto);

    @Mapping(target = "characterNotes", ignore = true)
    @Mapping(target = "campaigns", ignore = true)
    @Mapping(target = "characterSkills", ignore = true)
    void updateSqlDto(CharacterSheetApiDto characterSheetApiDto, @MappingTarget Ogl5CharacterSheet ogl5CharacterSheet);
}
