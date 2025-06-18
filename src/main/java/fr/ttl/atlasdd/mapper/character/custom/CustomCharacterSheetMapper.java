package fr.ttl.atlasdd.mapper.character.custom;

import fr.ttl.atlasdd.apidto.character.custom.CustomCharacterSheetApiDto;
import fr.ttl.atlasdd.entity.character.custom.CustomCharacterSheet;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CustomCharacterSheetMapper {

    CustomCharacterSheetApiDto toApiDto(CustomCharacterSheet characterSheetSqlDto);

    @Mapping(target = "characterNotes", ignore = true)
    @Mapping(target = "campaigns", ignore = true)
    CustomCharacterSheet toEntity(CustomCharacterSheetApiDto characterSheetApiDto);

    @Mapping(target = "characterNotes", ignore = true)
    @Mapping(target = "campaigns", ignore = true)
    void updateSqlDto(CustomCharacterSheetApiDto characterSheetApiDto, @MappingTarget CustomCharacterSheet characterSheetSqlDto);
}
