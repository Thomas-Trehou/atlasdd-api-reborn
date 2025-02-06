package fr.ttl.atlasdd.controller.user.usercontroller;

import fr.ttl.atlasdd.apidto.user.UserLightApiDto;
import fr.ttl.atlasdd.controller.user.UserController;
import fr.ttl.atlasdd.exception.GlobalExceptionHandler;
import fr.ttl.atlasdd.exception.user.UserNotFoundException;
import fr.ttl.atlasdd.service.user.UserService;
import fr.ttl.atlasdd.utils.exception.ExceptionMessage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;

import static org.mockito.Mockito.when;

public class GetFriendsTest {

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
    private static final Long TEST_ID = 1L;

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
        when(userService.getFriends(TEST_ID)).thenThrow(new UserNotFoundException(ExceptionMessage.USER_NOT_FOUND.getMessage()));

        mockMvc.perform(get("/users/{id}/friends", TEST_ID))
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.message").value(ExceptionMessage.USER_NOT_FOUND.getMessage()));
    }
}
