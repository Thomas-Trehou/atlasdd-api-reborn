package fr.ttl.atlasdd.mapper.character.custom;

import fr.ttl.atlasdd.apidto.character.custom.CustomArmorApiDto;
import fr.ttl.atlasdd.entity.character.custom.CustomArmor;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CustomArmorMapper {

    CustomArmorMapper INSTANCE = Mappers.getMapper(CustomArmorMapper.class);

    CustomArmorApiDto toApiDto(CustomArmor armorSqlDto);

    CustomArmor toSqlDto(CustomArmorApiDto armorApiDto);

    void updateSqlDto(CustomArmorApiDto armorApiDto, @MappingTarget CustomArmor armorSqlDto);
}
