package fr.ttl.atlasdd.mapper.campaign;

import fr.ttl.atlasdd.apidto.campaign.CampaignNoteApiDto;
import fr.ttl.atlasdd.entity.campaign.CampaignNote;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CampaignNoteMapper {

    CampaignNoteMapper INSTANCE = Mappers.getMapper(CampaignNoteMapper.class);

    CampaignNoteApiDto toApiDto(CampaignNote campaignNote);

    CampaignNote toSqlDto(CampaignNoteApiDto campaignNoteApiDto);

    void updateSqlDto(CampaignNoteApiDto campaignNoteApiDto,@MappingTarget CampaignNote campaignNote);
}
