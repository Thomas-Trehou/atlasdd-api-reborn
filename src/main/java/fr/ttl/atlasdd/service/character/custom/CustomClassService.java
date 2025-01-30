package fr.ttl.atlasdd.service.character.custom;

import fr.ttl.atlasdd.apidto.character.custom.CustomClassApiDto;
import org.springframework.stereotype.Service;

@Service
public interface CustomClassService {

    CustomClassApiDto createClass(CustomClassApiDto customClassApiDto);

    CustomClassApiDto updateClass(CustomClassApiDto customClassApiDto);
}
