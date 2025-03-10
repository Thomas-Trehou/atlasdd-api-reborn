package fr.ttl.atlasdd.mapper.campaign;

import fr.ttl.atlasdd.apidto.campaign.CampaignApiDto;
import fr.ttl.atlasdd.entity.campaign.Campaign;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CampaignMapper {

    CampaignApiDto toApiDto(Campaign campaign);

    Campaign toEntity(CampaignApiDto campaignApiDto);

    void updateSqlDto(CampaignApiDto campaignApiDto,@MappingTarget Campaign campaign);
}
