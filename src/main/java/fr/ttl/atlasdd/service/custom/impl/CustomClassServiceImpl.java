package fr.ttl.atlasdd.service.custom.impl;

import fr.ttl.atlasdd.apidto.custom.CustomClassApiDto;
import fr.ttl.atlasdd.mapper.custom.CustomClassMapper;
import fr.ttl.atlasdd.repository.custom.CustomClassRepo;
import fr.ttl.atlasdd.service.custom.CustomClassService;
import fr.ttl.atlasdd.sqldto.custom.CustomClassSqlDto;
import org.springframework.stereotype.Service;

@Service
public class CustomClassServiceImpl implements CustomClassService {

    private final CustomClassRepo customClassRepository;
    private final CustomClassMapper customClassMapper;

    public CustomClassServiceImpl(
            CustomClassRepo customClassRepository,
            CustomClassMapper customClassMapper
    ) {
        this.customClassRepository = customClassRepository;
        this.customClassMapper = customClassMapper;
    }

    @Override
    public CustomClassApiDto createClass(CustomClassApiDto customClassApiDto) {

        CustomClassSqlDto classSqlDto = customClassMapper.toSqlDto(customClassApiDto);
        CustomClassSqlDto savedClassSqlDto = customClassRepository.save(classSqlDto);

        return customClassMapper.toApiDto(savedClassSqlDto);
    }

    @Override
    public CustomClassApiDto updateClass(CustomClassApiDto customClassApiDto) {

        CustomClassSqlDto classSqlDto = customClassRepository.findById(customClassApiDto.getId()).orElseThrow();
        customClassMapper.updateSqlDto(customClassApiDto, classSqlDto);
        CustomClassSqlDto savedClassSqlDto = customClassRepository.save(classSqlDto);

        return customClassMapper.toApiDto(savedClassSqlDto);
    }
}
