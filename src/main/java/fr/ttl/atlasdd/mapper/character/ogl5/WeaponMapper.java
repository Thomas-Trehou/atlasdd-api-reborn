package fr.ttl.atlasdd.mapper.character.ogl5;

import fr.ttl.atlasdd.apidto.character.WeaponApiDto;
import fr.ttl.atlasdd.entity.character.ogl5.Ogl5Weapon;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface WeaponMapper {

    WeaponApiDto toApiDto(Ogl5Weapon ogl5Weapon);

    @Mapping(target = "characterSheets", ignore = true)
    Ogl5Weapon toEntity(WeaponApiDto weaponApiDto);

    @Mapping(target = "characterSheets", ignore = true)
    void updateSqlDto(WeaponApiDto weaponApiDto, @MappingTarget Ogl5Weapon ogl5Weapon);
}
