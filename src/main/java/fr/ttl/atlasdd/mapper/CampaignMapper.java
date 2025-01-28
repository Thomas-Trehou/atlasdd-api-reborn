package fr.ttl.atlasdd.mapper;

import fr.ttl.atlasdd.apidto.CampaignApiDto;
import fr.ttl.atlasdd.sqldto.CampaignSqlDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CampaignMapper {

    CampaignMapper INSTANCE = Mappers.getMapper(CampaignMapper.class);

    CampaignApiDto toApiDto(CampaignSqlDto campaignSqlDto);

    CampaignSqlDto toSqlDto(CampaignApiDto campaignApiDto);

    void updateSqlDto(CampaignApiDto campaignApiDto,@MappingTarget CampaignSqlDto campaignSqlDto);
}
