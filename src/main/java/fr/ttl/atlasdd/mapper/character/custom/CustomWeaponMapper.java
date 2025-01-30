package fr.ttl.atlasdd.mapper.character.custom;

import fr.ttl.atlasdd.apidto.character.custom.CustomWeaponApiDto;
import fr.ttl.atlasdd.sqldto.character.custom.CustomWeaponSqlDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CustomWeaponMapper {

    CustomWeaponMapper INSTANCE = Mappers.getMapper(CustomWeaponMapper.class);

    CustomWeaponApiDto toApiDto(CustomWeaponSqlDto weaponSqlDto);

    CustomWeaponSqlDto toSqlDto(CustomWeaponApiDto weaponApiDto);

    void updateSqlDto(CustomWeaponApiDto weaponApiDto, @MappingTarget CustomWeaponSqlDto weaponSqlDto);
}
