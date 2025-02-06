package fr.ttl.atlasdd.service.character.custom.impl;

import fr.ttl.atlasdd.apidto.character.custom.CustomClassApiDto;
import fr.ttl.atlasdd.exception.character.custom.notfound.CustomClassNotFoundException;
import fr.ttl.atlasdd.exception.character.custom.savingerror.CustomClassSavingErrorException;
import fr.ttl.atlasdd.mapper.character.custom.CustomClassMapper;
import fr.ttl.atlasdd.repository.character.custom.CustomClassRepo;
import fr.ttl.atlasdd.service.character.custom.CustomClassService;
import fr.ttl.atlasdd.sqldto.character.custom.CustomClassSqlDto;
import fr.ttl.atlasdd.utils.exception.ExceptionMessage;
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

        try {
            return customClassMapper.toApiDto(customClassRepository.save(classSqlDto));
        } catch (Exception e) {
            throw new CustomClassSavingErrorException(ExceptionMessage.CLASS_SAVE_ERROR.getMessage());
        }
    }

    @Override
    public CustomClassApiDto updateClass(CustomClassApiDto customClassApiDto) {

        CustomClassSqlDto classSqlDto = customClassRepository.findById(customClassApiDto.getId())
                .orElseThrow(() -> new CustomClassNotFoundException(ExceptionMessage.CLASS_NOT_FOUND.getMessage()));

        customClassMapper.updateSqlDto(customClassApiDto, classSqlDto);

        try {
            return customClassMapper.toApiDto(customClassRepository.save(classSqlDto));
        } catch (Exception e) {
            throw new CustomClassSavingErrorException(ExceptionMessage.CLASS_UPDATE_ERROR.getMessage());
        }
    }
}
