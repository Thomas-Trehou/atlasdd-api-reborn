package fr.ttl.atlasdd.service.user;

import fr.ttl.atlasdd.apidto.user.UserLightApiDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    UserLightApiDto getUserById(Long id);

    UserLightApiDto getUserBySlug(String slug);

    List<UserLightApiDto> getFriends(Long id);
}
