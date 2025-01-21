package fr.ttl.atlasdd.mapper.ogl5;

import fr.ttl.atlasdd.apidto.ogl5.SpellApiDto;
import fr.ttl.atlasdd.sqldto.ogl5.SpellSqlDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface SpellMapper {

    SpellMapper INSTANCE = Mappers.getMapper(SpellMapper.class);

    SpellApiDto toApiDto(SpellSqlDto spellSqlDto);

    SpellSqlDto toSqlDto(SpellApiDto spellApiDto);

    void updateSqlDto(SpellApiDto spellApiDto, @MappingTarget SpellSqlDto spellSqlDto);
}
