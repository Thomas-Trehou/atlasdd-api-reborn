package fr.ttl.atlasdd.mapper.campaign;

import fr.ttl.atlasdd.apidto.campaign.CampaignNoteApiDto;
import fr.ttl.atlasdd.entity.campaign.CampaignNote;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CampaignNoteMapper {

    CampaignNoteApiDto toApiDto(CampaignNote campaignNote);

    @Mapping(target = "owner", ignore = true)
    @Mapping(target = "campaign", ignore = true)
    CampaignNote toEntity(CampaignNoteApiDto campaignNoteApiDto);

    @Mapping(target = "owner", ignore = true)
    @Mapping(target = "campaign", ignore = true)
    void updateSqlDto(CampaignNoteApiDto campaignNoteApiDto, @MappingTarget CampaignNote campaignNote);
}
