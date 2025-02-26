package fr.ttl.atlasdd.mapper.character.ogl5;

import fr.ttl.atlasdd.apidto.character.ogl5.SpellApiDto;
import fr.ttl.atlasdd.entity.character.ogl5.Ogl5Spell;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface SpellMapper {

    SpellMapper INSTANCE = Mappers.getMapper(SpellMapper.class);

    SpellApiDto toApiDto(Ogl5Spell ogl5Spell);

    Ogl5Spell toSqlDto(SpellApiDto spellApiDto);

    void updateSqlDto(SpellApiDto spellApiDto, @MappingTarget Ogl5Spell ogl5Spell);
}
