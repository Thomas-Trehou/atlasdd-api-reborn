package fr.ttl.atlasdd.mapper.character.custom;

import fr.ttl.atlasdd.apidto.character.custom.CustomSkillApiDto;
import fr.ttl.atlasdd.sqldto.character.custom.CustomSkillSqlDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CustomSkillMapper {

    CustomSkillMapper INSTANCE = Mappers.getMapper(CustomSkillMapper.class);

    CustomSkillApiDto toApiDto(CustomSkillSqlDto skillSqlDto);

    CustomSkillSqlDto toSqlDto(CustomSkillApiDto skillApiDto);

    void updateFromApiDto(CustomSkillApiDto skillApiDto, @MappingTarget CustomSkillSqlDto skillSqlDto);
}
