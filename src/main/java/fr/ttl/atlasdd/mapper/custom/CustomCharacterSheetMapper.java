package fr.ttl.atlasdd.mapper.custom;

import fr.ttl.atlasdd.apidto.custom.CustomCharacterSheetApiDto;
import fr.ttl.atlasdd.sqldto.custom.CustomCharacterSheetSqlDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CustomCharacterSheetMapper {

    CustomCharacterSheetMapper INSTANCE = Mappers.getMapper(CustomCharacterSheetMapper.class);

    CustomCharacterSheetApiDto toApiDto(CustomCharacterSheetSqlDto characterSheetSqlDto);

    CustomCharacterSheetSqlDto toSqlDto(CustomCharacterSheetApiDto characterSheetApiDto);

    void updateSqlDto(CustomCharacterSheetApiDto characterSheetApiDto, @MappingTarget CustomCharacterSheetSqlDto characterSheetSqlDto);
}
