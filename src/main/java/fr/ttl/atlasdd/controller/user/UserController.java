package fr.ttl.atlasdd.controller.user;

import fr.ttl.atlasdd.apidto.user.SignInDto;
import fr.ttl.atlasdd.apidto.user.UserApiDto;
import fr.ttl.atlasdd.apidto.user.UserLightApiDto;
import fr.ttl.atlasdd.apidto.user.UserLightAuthApiDto;
import fr.ttl.atlasdd.service.user.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public UserLightApiDto getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @GetMapping("/{slug}")
    public UserLightApiDto getUserBySlug(@PathVariable String slug) {
        return userService.getUserBySlug(slug);
    }

    @GetMapping("/{id}/friends")
    public List<UserLightApiDto> getFriends(@PathVariable Long id) {
        return userService.getFriends(id);
    }

    @PostMapping("/signup")
    public UserLightApiDto createUser(@RequestBody UserApiDto userApiDto) {
        return userService.createUser(userApiDto);
    }

    @GetMapping("/verify")
    public String verifyToken(@RequestParam("token") String token, HttpSession session) {
        return userService.verifyToken(token, session);
    }

    @PostMapping("/signin")
    public UserLightAuthApiDto signIn(@RequestBody SignInDto signInDto) {
        return userService.signIn(signInDto);
    }
}
