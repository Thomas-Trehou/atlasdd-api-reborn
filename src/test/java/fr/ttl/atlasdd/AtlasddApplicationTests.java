package fr.ttl.atlasdd;

import fr.ttl.atlasdd.config.MockConfig;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@ActiveProfiles("test")
@TestPropertySource(locations = "classpath:application-test.properties")
@ContextConfiguration(classes = {MockConfig.class})
public class AtlasddApplicationTests {

    @Test
    public void contextLoads() {
    }
}
