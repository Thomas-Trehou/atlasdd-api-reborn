package fr.ttl.atlasdd.mapper.character.custom;

import fr.ttl.atlasdd.apidto.character.custom.CustomSpellApiDto;
import fr.ttl.atlasdd.entity.character.custom.CustomSpell;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CustomSpellMapper {

    CustomSpellMapper INSTANCE = Mappers.getMapper(CustomSpellMapper.class);

    CustomSpellApiDto toApiDto(CustomSpell spellSqlDto);

    CustomSpell toSqlDto(CustomSpellApiDto spellApiDto);

    void updateSqlDto(CustomSpellApiDto spellApiDto, @MappingTarget CustomSpell spellSqlDto);
}
