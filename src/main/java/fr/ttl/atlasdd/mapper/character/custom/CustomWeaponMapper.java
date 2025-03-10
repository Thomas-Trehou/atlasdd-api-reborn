package fr.ttl.atlasdd.mapper.character.custom;

import fr.ttl.atlasdd.apidto.character.custom.CustomWeaponApiDto;
import fr.ttl.atlasdd.entity.character.custom.CustomWeapon;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CustomWeaponMapper {

    CustomWeaponApiDto toApiDto(CustomWeapon weaponSqlDto);

    @Mapping(target = "characterSheets", ignore = true)
    CustomWeapon toEntity(CustomWeaponApiDto weaponApiDto);

    @Mapping(target = "characterSheets", ignore = true)
    void updateSqlDto(CustomWeaponApiDto weaponApiDto, @MappingTarget CustomWeapon weaponSqlDto);
}
