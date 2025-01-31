package fr.ttl.atlasdd.service.character.custom.impl;

import fr.ttl.atlasdd.apidto.character.custom.CustomClassApiDto;
import fr.ttl.atlasdd.exception.character.custom.CustomCharacterSavingErrorException;
import fr.ttl.atlasdd.exception.character.custom.CustomClassNotFoundException;
import fr.ttl.atlasdd.exception.character.custom.CustomClassSavingErrorException;
import fr.ttl.atlasdd.mapper.character.custom.CustomClassMapper;
import fr.ttl.atlasdd.repository.character.custom.CustomClassRepo;
import fr.ttl.atlasdd.service.character.custom.CustomClassService;
import fr.ttl.atlasdd.sqldto.character.custom.CustomClassSqlDto;
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
            throw new CustomClassSavingErrorException("Erreur lors de la sauvegarde de la classe", 500);
        }
    }

    @Override
    public CustomClassApiDto updateClass(CustomClassApiDto customClassApiDto) {

        CustomClassSqlDto classSqlDto = customClassRepository.findById(customClassApiDto.getId())
                .orElseThrow(() -> new CustomClassNotFoundException("Classe non trouvée", 404));

        customClassMapper.updateSqlDto(customClassApiDto, classSqlDto);

        try {
            return customClassMapper.toApiDto(customClassRepository.save(classSqlDto));
        } catch (Exception e) {
            throw new CustomClassSavingErrorException("Erreur lors de la sauvegarde de la classe", 500);
        }
    }
}
