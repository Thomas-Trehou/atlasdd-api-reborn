package fr.ttl.atlasdd.mapper.character.custom;

import fr.ttl.atlasdd.apidto.character.custom.CustomSkillApiDto;
import fr.ttl.atlasdd.entity.character.custom.CustomSkill;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CustomSkillMapper {

    CustomSkillMapper INSTANCE = Mappers.getMapper(CustomSkillMapper.class);

    CustomSkillApiDto toApiDto(CustomSkill skillSqlDto);

    CustomSkill toSqlDto(CustomSkillApiDto skillApiDto);

    void updateFromApiDto(CustomSkillApiDto skillApiDto, @MappingTarget CustomSkill skillSqlDto);
}
