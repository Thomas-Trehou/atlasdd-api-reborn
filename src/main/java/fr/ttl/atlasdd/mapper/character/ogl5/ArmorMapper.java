package fr.ttl.atlasdd.mapper.character.ogl5;

import fr.ttl.atlasdd.apidto.character.ogl5.ArmorApiDto;
import fr.ttl.atlasdd.entity.character.ogl5.Ogl5Armor;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ArmorMapper {

    ArmorApiDto toApiDto(Ogl5Armor ogl5Armor);

    Ogl5Armor toSqlDto(ArmorApiDto armorApiDto);

    void updateSqlDto(ArmorApiDto armorApiDto, @MappingTarget Ogl5Armor ogl5Armor);
}
