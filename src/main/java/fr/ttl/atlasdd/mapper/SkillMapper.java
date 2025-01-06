package fr.ttl.atlasdd.mapper;

import fr.ttl.atlasdd.apidto.SkillApiDto;
import fr.ttl.atlasdd.sqldto.SkillSqlDto;
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
