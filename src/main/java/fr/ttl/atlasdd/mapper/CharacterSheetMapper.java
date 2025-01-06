package fr.ttl.atlasdd.mapper;

import fr.ttl.atlasdd.apidto.CharacterSheetApiDto;
import fr.ttl.atlasdd.sqldto.CharacterSheetSqlDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CharacterSheetMapper {

    CharacterSheetMapper INSTANCE = Mappers.getMapper(CharacterSheetMapper.class);

    CharacterSheetApiDto toApiDto(CharacterSheetSqlDto characterSheetSqlDto);

    CharacterSheetSqlDto toSqlDto(CharacterSheetApiDto characterSheetApiDto);

    void updateSqlDto(CharacterSheetApiDto characterSheetApiDto, @MappingTarget CharacterSheetSqlDto characterSheetSqlDto);
}
