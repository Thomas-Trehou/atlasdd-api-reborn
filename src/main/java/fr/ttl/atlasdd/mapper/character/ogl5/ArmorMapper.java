package fr.ttl.atlasdd.mapper.character.ogl5;

import fr.ttl.atlasdd.apidto.character.ogl5.ArmorApiDto;
import fr.ttl.atlasdd.entity.character.ogl5.Ogl5Armor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ArmorMapper {

    ArmorApiDto toApiDto(Ogl5Armor ogl5Armor);

    @Mapping(target = "characterSheet", ignore = true)
    Ogl5Armor toEntity(ArmorApiDto armorApiDto);

    @Mapping(target = "characterSheet", ignore = true)
    void updateSqlDto(ArmorApiDto armorApiDto, @MappingTarget Ogl5Armor ogl5Armor);
}
