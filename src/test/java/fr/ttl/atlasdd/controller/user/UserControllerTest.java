package fr.ttl.atlasdd.controller.user;

import fr.ttl.atlasdd.apidto.user.UserApiDto;
import fr.ttl.atlasdd.apidto.user.UserLightApiDto;
import fr.ttl.atlasdd.exception.GlobalExceptionHandler;
import fr.ttl.atlasdd.exception.user.EmailAlreadyUsedException;
import fr.ttl.atlasdd.exception.user.PseudoAlreadyUsedException;
import fr.ttl.atlasdd.service.user.UserService;
import fr.ttl.atlasdd.utils.exception.ExceptionMessage;
import fr.ttl.atlasdd.apidto.user.SignInDto;
import fr.ttl.atlasdd.apidto.user.UserLightAuthApiDto;
import fr.ttl.atlasdd.exception.user.EmailNotVerifiedException;
import fr.ttl.atlasdd.exception.user.IncorrectEmailOrPasswordException;
import fr.ttl.atlasdd.exception.user.UserNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import com.fasterxml.jackson.databind.ObjectMapper;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.util.List;




class UserControllerTest {

    private MockMvc mockMvc;

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    // Constantes communes
    private static final String TEST_EMAIL = "test@example.com";
    private static final String TEST_PSEUDO = "testUser";
    private static final String TEST_SLUG = "testuser";
    private static final String TEST_PASSWORD = "password123";
    private static final String TEST_TOKEN = "eyJhbGciOiJIUzI1NiJ9.eyJlbWFpbCI6InRvbW1hbjU5QGdtYWlsLmNvbSIsImlhdCI6MTczODMxNTc5NiwiZXhwIjoxNzM4MzU4OTk2fQ.i0N8dGXRwEkQcmj9VjECrtMveM8qX-IYapmV1R6xmzI";
    private static final Long TEST_ID = 1L;

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

    // Tests pour createUser
    @Test
    void createUser_Success() throws Exception {
        UserApiDto requestDto = new UserApiDto();
        requestDto.setEmail(TEST_EMAIL);
        requestDto.setPseudo(TEST_PSEUDO);
        requestDto.setPassword(TEST_PASSWORD);

        UserLightApiDto responseDto = new UserLightApiDto();
        responseDto.setEmail(TEST_EMAIL);
        responseDto.setPseudo(TEST_PSEUDO);
        responseDto.setSlug(TEST_SLUG);
        responseDto.setId(TEST_ID);

        when(userService.createUser(any(UserApiDto.class))).thenReturn(responseDto);

        mockMvc.perform(post("/users/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(requestDto)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.email").value(TEST_EMAIL))
                .andExpect(jsonPath("$.pseudo").value(TEST_PSEUDO))
                .andExpect(jsonPath("$.slug").value(TEST_SLUG))
                .andExpect(jsonPath("$.id").value(TEST_ID));
    }

    @Test
    void createUser_EmailAlreadyExists() throws Exception {
        UserApiDto requestDto = new UserApiDto();
        requestDto.setEmail(TEST_EMAIL);
        requestDto.setPseudo(TEST_PSEUDO);
        requestDto.setPassword(TEST_PASSWORD);

        when(userService.createUser(any(UserApiDto.class)))
                .thenThrow(new EmailAlreadyUsedException(ExceptionMessage.USER_EMAIL_ALREADY_USED.getMessage()));

        mockMvc.perform(post("/users/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(requestDto)))
                .andExpect(status().isConflict())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.message").value(ExceptionMessage.USER_EMAIL_ALREADY_USED.getMessage()));
    }

    @Test
    void createUser_PseudoAlreadyExists() throws Exception {
        UserApiDto requestDto = new UserApiDto();
        requestDto.setEmail(TEST_EMAIL);
        requestDto.setPseudo(TEST_PSEUDO);
        requestDto.setPassword(TEST_PASSWORD);

        when(userService.createUser(any(UserApiDto.class)))
                .thenThrow(new PseudoAlreadyUsedException(ExceptionMessage.USER_PSEUDO_ALREADY_USED.getMessage()));

        mockMvc.perform(post("/users/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(requestDto)))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.message").value(ExceptionMessage.USER_PSEUDO_ALREADY_USED.getMessage()));
    }

    // Tests pour getFriends
    @Test
    void getFriends_Success() throws Exception {
        UserLightApiDto responseDto = new UserLightApiDto();
        responseDto.setEmail(TEST_EMAIL);
        responseDto.setPseudo(TEST_PSEUDO);
        responseDto.setSlug(TEST_SLUG);

        List<UserLightApiDto> friends = List.of(responseDto);

        when(userService.getFriends(TEST_ID)).thenReturn(friends);

        mockMvc.perform(get("/users/{id}/friends", TEST_ID))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].email").value(TEST_EMAIL))
                .andExpect(jsonPath("$[0].pseudo").value(TEST_PSEUDO))
                .andExpect(jsonPath("$[0].slug").value(TEST_SLUG));
    }

    @Test
    void getFriends_NotFound() throws Exception {
        when(userService.getFriends(TEST_ID))
                .thenThrow(new UserNotFoundException(ExceptionMessage.USER_NOT_FOUND.getMessage()));

        mockMvc.perform(get("/users/{id}/friends", TEST_ID))
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.message").value(ExceptionMessage.USER_NOT_FOUND.getMessage()));
    }

    // Tests pour signIn
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
        responseDto.setId(TEST_ID);

        when(userService.signIn(any(SignInDto.class))).thenReturn(responseDto);

        mockMvc.perform(post("/users/signin")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(requestDto)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.email").value(TEST_EMAIL))
                .andExpect(jsonPath("$.pseudo").value(TEST_PSEUDO))
                .andExpect(jsonPath("$.slug").value(TEST_SLUG))
                .andExpect(jsonPath("$.id").value(TEST_ID))
                .andExpect(jsonPath("$.token").value(TEST_TOKEN));
    }

    @Test
    void signIn_IncorrectPasswordOrEmail() throws Exception {
        SignInDto requestDto = new SignInDto();
        requestDto.setEmail(TEST_EMAIL);
        requestDto.setPassword(TEST_PASSWORD);

        when(userService.signIn(any(SignInDto.class)))
                .thenThrow(new IncorrectEmailOrPasswordException(ExceptionMessage.USER_EMAIL_OR_PASSWORD_INVALID.getMessage()));

        mockMvc.perform(post("/users/signin")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(requestDto)))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.message").value(ExceptionMessage.USER_EMAIL_OR_PASSWORD_INVALID.getMessage()));
    }

    @Test
    void signIn_EmailNotVerified() throws Exception {
        SignInDto requestDto = new SignInDto();
        requestDto.setEmail(TEST_EMAIL);
        requestDto.setPassword(TEST_PASSWORD);

        when(userService.signIn(any(SignInDto.class)))
                .thenThrow(new EmailNotVerifiedException(ExceptionMessage.USER_EMAIL_NOT_VERIFIED.getMessage()));

        mockMvc.perform(post("/users/signin")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(requestDto)))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.message").value(ExceptionMessage.USER_EMAIL_NOT_VERIFIED.getMessage()));
    }

    private String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
