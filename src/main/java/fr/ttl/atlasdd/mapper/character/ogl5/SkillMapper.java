package fr.ttl.atlasdd.mapper.character.ogl5;

import fr.ttl.atlasdd.apidto.character.ogl5.SkillApiDto;
import fr.ttl.atlasdd.entity.character.ogl5.Ogl5Skill;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface SkillMapper {

    SkillMapper INSTANCE = Mappers.getMapper(SkillMapper.class);

    SkillApiDto toApiDto(Ogl5Skill ogl5Skill);

    Ogl5Skill toSqlDto(SkillApiDto skillApiDto);

    void updateFromApiDto(SkillApiDto skillApiDto, @MappingTarget Ogl5Skill ogl5Skill);
}
