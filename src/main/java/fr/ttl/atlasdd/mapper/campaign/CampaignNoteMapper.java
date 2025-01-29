package fr.ttl.atlasdd.mapper.campaign;

import fr.ttl.atlasdd.apidto.campaign.CampaignNoteApiDto;
import fr.ttl.atlasdd.sqldto.campaign.CampaignNoteSqlDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CampaignNoteMapper {

    CampaignNoteMapper INSTANCE = Mappers.getMapper(CampaignNoteMapper.class);

    CampaignNoteApiDto toApiDto(CampaignNoteSqlDto campaignNoteSqlDto);

    CampaignNoteSqlDto toSqlDto(CampaignNoteApiDto campaignNoteApiDto);

    void updateSqlDto(CampaignNoteApiDto campaignNoteApiDto,@MappingTarget CampaignNoteSqlDto campaignNoteSqlDto);
}
