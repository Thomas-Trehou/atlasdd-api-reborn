package fr.ttl.atlasdd.mapper.campaign;

import fr.ttl.atlasdd.apidto.campaign.CampaignApiDto;
import fr.ttl.atlasdd.entity.campaign.Campaign;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CampaignMapper {

    CampaignApiDto toApiDto(Campaign campaign);

    @Mapping(target = "campaignOgl5CharacterSheets", ignore = true)
    @Mapping(target = "campaignNotes", ignore = true)
    @Mapping(target = "campaignCustomCharacterSheets", ignore = true)
    Campaign toEntity(CampaignApiDto campaignApiDto);

    @Mapping(target = "campaignOgl5CharacterSheets", ignore = true)
    @Mapping(target = "campaignNotes", ignore = true)
    @Mapping(target = "campaignCustomCharacterSheets", ignore = true)
    void updateSqlDto(CampaignApiDto campaignApiDto, @MappingTarget Campaign campaign);
}
