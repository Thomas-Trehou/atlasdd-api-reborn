package fr.ttl.atlasdd.mapper.character.ogl5;

import fr.ttl.atlasdd.apidto.character.ogl5.SkillApiDto;
import fr.ttl.atlasdd.sqldto.character.ogl5.SkillSqlDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface SkillMapper {

    SkillMapper INSTANCE = Mappers.getMapper(SkillMapper.class);

    SkillApiDto toApiDto(SkillSqlDto skillSqlDto);

    SkillSqlDto toSqlDto(SkillApiDto skillApiDto);

    void updateFromApiDto(SkillApiDto skillApiDto, @MappingTarget SkillSqlDto skillSqlDto);
}
