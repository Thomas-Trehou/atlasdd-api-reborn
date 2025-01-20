package fr.ttl.atlasdd.mapper;

import fr.ttl.atlasdd.apidto.NoteCharacterApiDto;
import fr.ttl.atlasdd.sqldto.NoteCharacterSqlDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public interface NoteCharacterMapper {

    NoteCharacterMapper INSTANCE = Mappers.getMapper(NoteCharacterMapper.class);

    NoteCharacterApiDto toApiDto(NoteCharacterSqlDto noteCharacterSqlDto);

    NoteCharacterSqlDto toSqlDto(NoteCharacterApiDto noteCharacterApiDto);

    void updateSqlDto(NoteCharacterApiDto noteCharacterApiDto, @MappingTarget NoteCharacterSqlDto noteCharacterSqlDto);
}
