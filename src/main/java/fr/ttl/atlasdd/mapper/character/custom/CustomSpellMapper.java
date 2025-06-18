package fr.ttl.atlasdd.mapper.character.custom;

import fr.ttl.atlasdd.apidto.character.SpellApiDto;
import fr.ttl.atlasdd.entity.character.custom.CustomSpell;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CustomSpellMapper {

    SpellApiDto toApiDto(CustomSpell spellSqlDto);

    @Mapping(target = "characterSheets", ignore = true)
    CustomSpell toEntity(SpellApiDto spellApiDto);

    @Mapping(target = "characterSheets", ignore = true)
    void updateSqlDto(SpellApiDto spellApiDto, @MappingTarget CustomSpell spellSqlDto);
}
