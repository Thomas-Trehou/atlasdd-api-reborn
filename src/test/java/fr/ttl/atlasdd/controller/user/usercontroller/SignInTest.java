package fr.ttl.atlasdd.controller.user.usercontroller;

import fr.ttl.atlasdd.apidto.user.SignInDto;
import fr.ttl.atlasdd.apidto.user.UserLightAuthApiDto;
import fr.ttl.atlasdd.controller.user.UserController;
import fr.ttl.atlasdd.exception.GlobalExceptionHandler;
import fr.ttl.atlasdd.exception.user.EmailNotVerifiedException;
import fr.ttl.atlasdd.exception.user.IncorrectEmailOrPasswordException;
import fr.ttl.atlasdd.service.user.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.http.MediaType;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import com.fasterxml.jackson.databind.ObjectMapper;

public class SignInTest {

    private MockMvc mockMvc;

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    void setUp() {
        try (AutoCloseable mocks = MockitoAnnotations.openMocks(this)) {
            mockMvc = MockMvcBuilders.standaloneSetup(userController)
                    .setControllerAdvice(new GlobalExceptionHandler())
                    .build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static final String TEST_EMAIL = "test@example.com";
    private static final String TEST_PSEUDO = "testUser";
    private static final String TEST_SLUG = "testuser";
    private static final String TEST_PASSWORD = "password123";
    private static final String TEST_TOKEN = "eyJhbGciOiJIUzI1NiJ9.eyJlbWFpbCI6InRvbW1hbjU5QGdtYWlsLmNvbSIsImlhdCI6MTczODMxNTc5NiwiZXhwIjoxNzM4MzU4OTk2fQ.i0N8dGXRwEkQcmj9VjECrtMveM8qX-IYapmV1R6xmzI";

    @Test
    void signIn_Success() throws Exception {
        SignInDto requestDto = new SignInDto();
        requestDto.setEmail(TEST_EMAIL);
        requestDto.setPassword(TEST_PASSWORD);

        UserLightAuthApiDto responseDto = new UserLightAuthApiDto();
        responseDto.setEmail(TEST_EMAIL);
        responseDto.setPseudo(TEST_PSEUDO);
        responseDto.setSlug(TEST_SLUG);
        responseDto.setToken(TEST_TOKEN);
        responseDto.setId(1L);

        when(userService.signIn(any(SignInDto.class))).thenReturn(responseDto);

        mockMvc.perform(post("/users/signin")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(requestDto)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.email").value(TEST_EMAIL))
                .andExpect(jsonPath("$.pseudo").value(TEST_PSEUDO))
                .andExpect(jsonPath("$.slug").value(TEST_SLUG))
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.token").value(TEST_TOKEN));
    }

    @Test
    void signIn_IncorrectPasswordOrEmail() throws Exception {
        SignInDto requestDto = new SignInDto();
        requestDto.setEmail(TEST_EMAIL);
        requestDto.setPassword(TEST_PASSWORD);

        when(userService.signIn(any(SignInDto.class))).thenThrow(new IncorrectEmailOrPasswordException("Email ou mot de passe incorrect"));

        mockMvc.perform(post("/users/signin")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(requestDto)))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.message").value("Email ou mot de passe incorrect"));
    }

    @Test
    void signIn_EmailNotVerified() throws Exception {
        SignInDto requestDto = new SignInDto();
        requestDto.setEmail(TEST_EMAIL);
        requestDto.setPassword(TEST_PASSWORD);

        when(userService.signIn(any(SignInDto.class))).thenThrow(new EmailNotVerifiedException("Vous devez vérifier votre adresse email"));

        mockMvc.perform(post("/users/signin")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(requestDto)))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.message").value("Vous devez vérifier votre adresse email"));
    }
}
