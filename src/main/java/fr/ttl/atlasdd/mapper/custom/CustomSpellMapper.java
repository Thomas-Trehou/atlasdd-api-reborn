package fr.ttl.atlasdd.mapper.custom;

import fr.ttl.atlasdd.apidto.custom.CustomSpellApiDto;
import fr.ttl.atlasdd.sqldto.custom.CustomSpellSqlDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CustomSpellMapper {

    CustomSpellMapper INSTANCE = Mappers.getMapper(CustomSpellMapper.class);

    CustomSpellApiDto toApiDto(CustomSpellSqlDto spellSqlDto);

    CustomSpellSqlDto toSqlDto(CustomSpellApiDto spellApiDto);

    void updateSqlDto(CustomSpellApiDto spellApiDto, @MappingTarget CustomSpellSqlDto spellSqlDto);
}
