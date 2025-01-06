package fr.ttl.atlasdd.mapper;

import fr.ttl.atlasdd.apidto.SpellApiDto;
import fr.ttl.atlasdd.sqldto.SpellSqlDto;
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
