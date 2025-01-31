package fr.ttl.atlasdd.controller.user;

import fr.ttl.atlasdd.apidto.user.*;
import fr.ttl.atlasdd.service.user.UserService;
import fr.ttl.atlasdd.utils.user.JwtTokenProvider;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final JwtTokenProvider tokenProvider;

    public UserController(UserService userService, JwtTokenProvider tokenProvider) {
        this.userService = userService;
        this.tokenProvider = tokenProvider;
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
