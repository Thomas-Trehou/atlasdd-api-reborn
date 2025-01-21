package fr.ttl.atlasdd.mapper.ogl5;

import fr.ttl.atlasdd.apidto.ogl5.WeaponApiDto;
import fr.ttl.atlasdd.sqldto.ogl5.WeaponSqlDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface WeaponMapper {

    WeaponMapper INSTANCE = Mappers.getMapper(WeaponMapper.class);

    WeaponApiDto toApiDto(WeaponSqlDto weaponSqlDto);

    WeaponSqlDto toSqlDto(WeaponApiDto weaponApiDto);

    void updateSqlDto(WeaponApiDto weaponApiDto, @MappingTarget WeaponSqlDto weaponSqlDto);
}
