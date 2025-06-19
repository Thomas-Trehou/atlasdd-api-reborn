
package fr.ttl.atlasdd.mapper.character.ogl5;

import fr.ttl.atlasdd.apidto.character.CharacterSkillDto;
import fr.ttl.atlasdd.entity.character.custom.CustomCharacterSkill;
import fr.ttl.atlasdd.entity.character.ogl5.Ogl5CharacterSkill;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface CharacterSkillMapper {

    @Mapping(target = "id", source = "skill.id")
    @Mapping(target = "name", source = "skill.name")
    @Mapping(target = "expert", source = "expert")
    CharacterSkillDto toApiDto(Ogl5CharacterSkill ogl5CharacterSkill);

    @Mapping(target = "skill", ignore = true)
    @Mapping(target = "characterSheet", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    Ogl5CharacterSkill toEntity(CharacterSkillDto characterSkillDto);

    @Mapping(target = "id", source = "skill.id")
    @Mapping(target = "name", source = "skill.name")
    @Mapping(target = "expert", source = "expert")
    CharacterSkillDto toCustomApiDto(CustomCharacterSkill customCharacterSkill);

    @Mapping(target = "skill", ignore = true)
    @Mapping(target = "characterSheet", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    CustomCharacterSkill toCustomEntity(CharacterSkillDto characterSkillDto);
}