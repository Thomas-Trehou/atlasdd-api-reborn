package fr.ttl.atlasdd.mapper.ogl5;

import fr.ttl.atlasdd.apidto.ogl5.ArmorApiDto;
import fr.ttl.atlasdd.sqldto.ogl5.ArmorSqlDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ArmorMapper {

    ArmorMapper INSTANCE = Mappers.getMapper(ArmorMapper.class);

    ArmorApiDto toApiDto(ArmorSqlDto armorSqlDto);

    ArmorSqlDto toSqlDto(ArmorApiDto armorApiDto);

    void updateSqlDto(ArmorApiDto armorApiDto, @MappingTarget ArmorSqlDto armorSqlDto);
}
