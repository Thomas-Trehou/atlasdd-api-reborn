package fr.ttl.atlasdd.mapper.campaign;

import fr.ttl.atlasdd.apidto.campaign.CampaignNoteApiDto;
import fr.ttl.atlasdd.entity.campaign.CampaignNote;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CampaignNoteMapper {

    CampaignNoteApiDto toApiDto(CampaignNote campaignNote);

    CampaignNote toSqlDto(CampaignNoteApiDto campaignNoteApiDto);

    void updateSqlDto(CampaignNoteApiDto campaignNoteApiDto,@MappingTarget CampaignNote campaignNote);
}
