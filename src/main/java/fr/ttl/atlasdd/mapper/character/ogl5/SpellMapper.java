package fr.ttl.atlasdd.mapper.character.ogl5;

import fr.ttl.atlasdd.apidto.character.ogl5.SpellApiDto;
import fr.ttl.atlasdd.entity.character.ogl5.Ogl5Spell;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface SpellMapper {

    SpellApiDto toApiDto(Ogl5Spell ogl5Spell);

    @Mapping(target = "spellRaces", ignore = true)
    @Mapping(target = "spellClasses", ignore = true)
    @Mapping(target = "characterSheets", ignore = true)
    Ogl5Spell toEntity(SpellApiDto spellApiDto);

    @Mapping(target = "spellRaces", ignore = true)
    @Mapping(target = "spellClasses", ignore = true)
    @Mapping(target = "characterSheets", ignore = true)
    void updateSqlDto(SpellApiDto spellApiDto, @MappingTarget Ogl5Spell ogl5Spell);
}
