package fr.ttl.atlasdd.mapper.character.custom;

import fr.ttl.atlasdd.apidto.character.ArmorApiDto;
import fr.ttl.atlasdd.entity.character.custom.CustomArmor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CustomArmorMapper {

    ArmorApiDto toApiDto(CustomArmor armorSqlDto);

    @Mapping(target = "characterSheet", ignore = true)
    CustomArmor toEntity(ArmorApiDto armorApiDto);

    @Mapping(target = "characterSheet", ignore = true)
    void updateSqlDto(ArmorApiDto armorApiDto, @MappingTarget CustomArmor armorSqlDto);
}
