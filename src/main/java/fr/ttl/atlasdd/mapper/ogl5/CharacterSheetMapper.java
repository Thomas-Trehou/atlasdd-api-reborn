package fr.ttl.atlasdd.mapper.ogl5;

import fr.ttl.atlasdd.apidto.ogl5.CharacterSheetApiDto;
import fr.ttl.atlasdd.sqldto.ogl5.CharacterSheetSqlDto;
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
