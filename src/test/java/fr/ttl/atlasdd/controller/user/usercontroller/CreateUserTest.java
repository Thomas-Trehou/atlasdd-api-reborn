package fr.ttl.atlasdd.controller.user.usercontroller;

import fr.ttl.atlasdd.apidto.user.UserApiDto;
import fr.ttl.atlasdd.apidto.user.UserLightApiDto;
import fr.ttl.atlasdd.controller.user.UserController;
import fr.ttl.atlasdd.exception.GlobalExceptionHandler;
import fr.ttl.atlasdd.exception.user.EmailAlreadyUsedException;
import fr.ttl.atlasdd.exception.user.PseudoAlreadyUsedException;
import fr.ttl.atlasdd.service.user.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import com.fasterxml.jackson.databind.ObjectMapper;

public class CreateUserTest {

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
        responseDto.setId(1L);

        when(userService.createUser(any(UserApiDto.class))).thenReturn(responseDto);

        mockMvc.perform(post("/users/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(requestDto)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.email").value(TEST_EMAIL))
                .andExpect(jsonPath("$.pseudo").value(TEST_PSEUDO))
                .andExpect(jsonPath("$.slug").value(TEST_SLUG))
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    void createUser_EmailAlreadyExists() throws Exception {

        UserApiDto requestDto = new UserApiDto();
        requestDto.setEmail(TEST_EMAIL);
        requestDto.setPseudo(TEST_PSEUDO);
        requestDto.setPassword(TEST_PASSWORD);

        when(userService.createUser(any(UserApiDto.class)))
                .thenThrow(new EmailAlreadyUsedException("Email déjà utilisé"));

        mockMvc.perform(post("/users/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(requestDto)))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.message").value("Email déjà utilisé"));

    }

    @Test
    void createUser_PseudoAlreadyExists() throws Exception {

        UserApiDto requestDto = new UserApiDto();
        requestDto.setEmail(TEST_EMAIL);
        requestDto.setPseudo(TEST_PSEUDO);
        requestDto.setPassword(TEST_PASSWORD);

        when(userService.createUser(any(UserApiDto.class)))
                .thenThrow(new PseudoAlreadyUsedException("Pseudo déjà utilisé"));

        mockMvc.perform(post("/users/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(requestDto)))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.message").value("Pseudo déjà utilisé"));
    }

    private String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
