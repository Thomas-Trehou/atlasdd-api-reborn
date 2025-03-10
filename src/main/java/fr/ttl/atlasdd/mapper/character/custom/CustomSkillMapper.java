package fr.ttl.atlasdd.mapper.character.custom;

import fr.ttl.atlasdd.apidto.character.custom.CustomSkillApiDto;
import fr.ttl.atlasdd.entity.character.custom.CustomSkill;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CustomSkillMapper {

    CustomSkillApiDto toApiDto(CustomSkill skillSqlDto);

    CustomSkill toEntity(CustomSkillApiDto skillApiDto);

    void updateFromApiDto(CustomSkillApiDto skillApiDto, @MappingTarget CustomSkill skillSqlDto);
}
