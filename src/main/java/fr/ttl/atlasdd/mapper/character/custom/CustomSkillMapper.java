package fr.ttl.atlasdd.mapper.character.custom;

import fr.ttl.atlasdd.apidto.character.SkillApiDto;
import fr.ttl.atlasdd.entity.character.custom.CustomSkill;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CustomSkillMapper {

    SkillApiDto toApiDto(CustomSkill skillSqlDto);

    @Mapping(target = "characterSkills", ignore = true)
    CustomSkill toEntity(SkillApiDto skillApiDto);

    @Mapping(target = "characterSkills", ignore = true)
    void updateFromApiDto(SkillApiDto skillApiDto, @MappingTarget CustomSkill skillSqlDto);
}
