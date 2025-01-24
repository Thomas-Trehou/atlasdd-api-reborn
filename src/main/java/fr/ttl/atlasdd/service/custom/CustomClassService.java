package fr.ttl.atlasdd.service.custom;

import fr.ttl.atlasdd.apidto.custom.CustomClassApiDto;
import org.springframework.stereotype.Service;

@Service
public interface CustomClassService {

    CustomClassApiDto createClass(CustomClassApiDto customClassApiDto);

    CustomClassApiDto updateClass(CustomClassApiDto customClassApiDto);
}
