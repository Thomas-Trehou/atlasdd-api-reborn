package fr.ttl.atlasdd.mapper.character.ogl5;

import fr.ttl.atlasdd.apidto.character.SkillApiDto;
import fr.ttl.atlasdd.entity.character.ogl5.Ogl5Skill;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface SkillMapper {

    SkillApiDto toApiDto(Ogl5Skill ogl5Skill);

    @Mapping(target = "characterSheets", ignore = true)
    Ogl5Skill toEntity(SkillApiDto skillApiDto);

    @Mapping(target = "characterSheets", ignore = true)
    void updateFromApiDto(SkillApiDto skillApiDto, @MappingTarget Ogl5Skill ogl5Skill);
}
