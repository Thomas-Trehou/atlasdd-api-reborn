package fr.ttl.atlasdd.service.user;

import fr.ttl.atlasdd.apidto.user.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    UserLightApiDto getCurrentUser(String token);

    UserLightApiDto getUserById(Long id);

    UserLightApiDto getUserBySlug(String slug);

    List<UserLightApiDto> getFriends(Long id);

    UserLightApiDto createUser(UserApiDto userApiDto);

    String verifyToken(String token, HttpSession session);

    UserLightAuthApiDto signIn(SignInDto signInDto);

    void deleteUser(Long id);

    UserLightApiDto updateProfile(Long id, ProfileUpdateApiDto profileUpdateApiDto);
}
