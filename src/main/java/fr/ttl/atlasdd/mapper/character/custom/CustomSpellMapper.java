package fr.ttl.atlasdd.mapper.character.custom;

import fr.ttl.atlasdd.apidto.character.custom.CustomSpellApiDto;
import fr.ttl.atlasdd.entity.character.custom.CustomSpell;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CustomSpellMapper {

    CustomSpellApiDto toApiDto(CustomSpell spellSqlDto);

    @Mapping(target = "characterSheets", ignore = true)
    CustomSpell toEntity(CustomSpellApiDto spellApiDto);

    @Mapping(target = "characterSheets", ignore = true)
    void updateSqlDto(CustomSpellApiDto spellApiDto, @MappingTarget CustomSpell spellSqlDto);
}
