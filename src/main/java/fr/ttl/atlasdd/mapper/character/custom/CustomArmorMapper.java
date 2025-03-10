package fr.ttl.atlasdd.mapper.character.custom;

import fr.ttl.atlasdd.apidto.character.custom.CustomArmorApiDto;
import fr.ttl.atlasdd.entity.character.custom.CustomArmor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CustomArmorMapper {

    CustomArmorApiDto toApiDto(CustomArmor armorSqlDto);

    @Mapping(target = "characterSheet", ignore = true)
    CustomArmor toEntity(CustomArmorApiDto armorApiDto);

    @Mapping(target = "characterSheet", ignore = true)
    void updateSqlDto(CustomArmorApiDto armorApiDto, @MappingTarget CustomArmor armorSqlDto);
}
