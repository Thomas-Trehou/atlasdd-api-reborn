package fr.ttl.atlasdd.controller;

import fr.ttl.atlasdd.apidto.UserLightApiDto;
import fr.ttl.atlasdd.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
