package fr.ttl.atlasdd.mapper.character.custom;

import fr.ttl.atlasdd.apidto.character.WeaponApiDto;
import fr.ttl.atlasdd.entity.character.custom.CustomWeapon;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CustomWeaponMapper {

    WeaponApiDto toApiDto(CustomWeapon weaponSqlDto);

    @Mapping(target = "characterSheets", ignore = true)
    CustomWeapon toEntity(WeaponApiDto weaponApiDto);

    @Mapping(target = "characterSheets", ignore = true)
    void updateSqlDto(WeaponApiDto weaponApiDto, @MappingTarget CustomWeapon weaponSqlDto);
}
