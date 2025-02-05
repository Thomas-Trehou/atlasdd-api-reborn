package fr.ttl.atlasdd.controller.user;

import fr.ttl.atlasdd.apidto.user.*;
import fr.ttl.atlasdd.service.user.UserService;
import fr.ttl.atlasdd.utils.user.JwtTokenProvider;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(summary = "Get one user by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User found, returns the user"),
            @ApiResponse(responseCode = "404", description = "User not found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Error at user retrieval", content = @Content)
    })
    @GetMapping("/{id}")
    public UserLightApiDto getUserById(
            @Parameter(description = "ID of the user", required = true)
            @PathVariable Long id
    ) {
        return userService.getUserById(id);
    }

    @Operation(summary = "Get one user by slug")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User found, returns the user"),
            @ApiResponse(responseCode = "404", description = "User not found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Error at user retrieval", content = @Content)
    })
    @GetMapping("/{slug}")
    public UserLightApiDto getUserBySlug(
            @Parameter(description = "Slug of the user", required = true)
            @PathVariable String slug
    ) {
        return userService.getUserBySlug(slug);
    }

    @Operation(summary = "Get all friends of a user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Friends found, returns the friends"),
            @ApiResponse(responseCode = "404", description = "User not found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Error at friends retrieval", content = @Content)
    })
    @GetMapping("/{id}/friends")
    public List<UserLightApiDto> getFriends(
            @Parameter(description = "ID of the user", required = true)
            @PathVariable Long id
    ) {
        return userService.getFriends(id);
    }

    @Operation(summary = "Create a user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User created, returns the created user and session, send a mail to verify the email"),
            @ApiResponse(responseCode = "400", description =
                    "Email already used / " +
                    "Pseudo already used", content = @Content),
            @ApiResponse(responseCode = "500", description = "Error at user creation", content = @Content)
    })
    @PostMapping("/signup")
    public UserLightApiDto createUser(
            @Parameter(description = "User to create", required = true)
            @RequestBody UserApiDto userApiDto
    ) {
        return userService.createUser(userApiDto);
    }

    @Operation(summary = "Verify a user email")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Email verified"),
            @ApiResponse(responseCode = "400", description = "Invalid token", content = @Content),
            @ApiResponse(responseCode = "500", description = "Error at email verification", content = @Content)
    })
    @GetMapping("/verify")
    public String verifyToken(
            @Parameter(description = "Token to verify", required = true)
            @RequestParam("token") String token,
            @Parameter(description = "Session", required = true)
            HttpSession session
    ) {
        return userService.verifyToken(token, session);
    }

    @Operation(summary = "Sign in a user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User signed in, returns the authenticated user"),
            @ApiResponse(responseCode = "400", description =
                    "Invalid credentials / " +
                    "You should verify your mail adress", content = @Content),
            @ApiResponse(responseCode = "500", description = "Error at user sign in", content = @Content)
    })
    @PostMapping("/signin")
    public UserLightAuthApiDto signIn(
            @Parameter(description = "Sign in data", required = true)
            @RequestBody SignInDto signInDto
    ) {
        return userService.signIn(signInDto);
    }
}
