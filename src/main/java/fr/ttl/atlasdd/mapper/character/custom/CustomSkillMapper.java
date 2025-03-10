package fr.ttl.atlasdd.mapper.character.custom;

import fr.ttl.atlasdd.apidto.character.custom.CustomSkillApiDto;
import fr.ttl.atlasdd.entity.character.custom.CustomSkill;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CustomSkillMapper {

    CustomSkillApiDto toApiDto(CustomSkill skillSqlDto);

    @Mapping(target = "characterSheets", ignore = true)
    CustomSkill toEntity(CustomSkillApiDto skillApiDto);

    @Mapping(target = "characterSheets", ignore = true)
    void updateFromApiDto(CustomSkillApiDto skillApiDto, @MappingTarget CustomSkill skillSqlDto);
}
