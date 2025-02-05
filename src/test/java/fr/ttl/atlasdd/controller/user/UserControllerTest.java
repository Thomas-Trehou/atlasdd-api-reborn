package fr.ttl.atlasdd.controller.user;

import fr.ttl.atlasdd.apidto.user.UserApiDto;
import fr.ttl.atlasdd.apidto.user.UserLightApiDto;
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

public class UserControllerTest {

    private MockMvc mockMvc;

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    void setUp() {
        try (AutoCloseable mocks = MockitoAnnotations.openMocks(this)) {
            mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void testSignUp() throws Exception {
        UserApiDto userApiDto = new UserApiDto();
        UserLightApiDto userLightApiDto = new UserLightApiDto();
        userLightApiDto.setEmail("email@test.com");
        userLightApiDto.setPseudo("nom");
        userLightApiDto.setSlug("nom");

        when(userService.createUser(any(UserApiDto.class))).thenReturn(userLightApiDto);

        mockMvc.perform(post("/users/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\n" +
                                "    \"pseudo\": \"nom\",\n" +
                                "    \"slug\": \"nom\",\n" +
                                "    \"email\": \"email@test.com\",\n" +
                                "    \"password\": \"password\"\n" +
                                "}"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.email").value("email@test.com"))
                .andExpect(jsonPath("$.pseudo").value("nom"))
                .andExpect(jsonPath("$.slug").value("nom"));
    }
}
