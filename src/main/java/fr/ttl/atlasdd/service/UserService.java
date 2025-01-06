package fr.ttl.atlasdd.service;

import fr.ttl.atlasdd.apidto.UserLightApiDto;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    UserLightApiDto getUserById(Long id);

    UserLightApiDto getUserBySlug(String slug);
}
