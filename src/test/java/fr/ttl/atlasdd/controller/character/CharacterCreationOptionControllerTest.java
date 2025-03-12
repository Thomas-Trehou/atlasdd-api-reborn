package fr.ttl.atlasdd.controller.character;

import fr.ttl.atlasdd.apidto.character.*;
import fr.ttl.atlasdd.apidto.character.ogl5.ClassApiDto;
import fr.ttl.atlasdd.apidto.character.ogl5.RaceApiDto;
import fr.ttl.atlasdd.service.character.CharacterCreationOptionService;
import fr.ttl.atlasdd.utils.character.ArmorCategory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class CharacterCreationOptionControllerTest {

    private MockMvc mockMvc;

    @Mock
    private CharacterCreationOptionService characterCreationOptionService;

    @InjectMocks
    private CharacterCreationOptionController characterCreationOptionController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(characterCreationOptionController).build();
    }

    @Test
    void getRaces_Success() throws Exception {
        
        List<RaceApiDto> races = Arrays.asList(
                new RaceApiDto("Elf", "Graceful and long-lived", 0,0,0,0,0,0, "", "", new ArrayList<>()),
                new RaceApiDto("Dwarf", "Hardy and strong",0,0,0,0,0,0, "", "", new ArrayList<>())
        );
        when(characterCreationOptionService.getRaces()).thenReturn(races);

        
        mockMvc.perform(get("/character-creation-options/races")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].name", is("Elf")))
                .andExpect(jsonPath("$[1].name", is("Dwarf")));

        verify(characterCreationOptionService, times(1)).getRaces();
    }

    @Test
    void getClasses_Success() throws Exception {
        
        List<ClassApiDto> classes = Arrays.asList(
                new ClassApiDto("Fighter", "Skilled in combat",0, "", new ArrayList<>()),
                new ClassApiDto("Wizard", "Master of arcane",0, "", new ArrayList<>())
        );
        when(characterCreationOptionService.getClasses()).thenReturn(classes);

        
        mockMvc.perform(get("/character-creation-options/classes")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].name", is("Fighter")))
                .andExpect(jsonPath("$[1].name", is("Wizard")));

        verify(characterCreationOptionService, times(1)).getClasses();
    }

    @Test
    void getBackgrounds_Success() throws Exception {
        
        List<BackgroundApiDto> backgrounds = Arrays.asList(
                new BackgroundApiDto("Soldier", "Military experience", "", ""),
                new BackgroundApiDto("Scholar", "Academic knowledge", "", "")
        );
        when(characterCreationOptionService.getBackgrounds()).thenReturn(backgrounds);

        
        mockMvc.perform(get("/character-creation-options/backgrounds")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].name", is("Soldier")))
                .andExpect(jsonPath("$[1].name", is("Scholar")));

        verify(characterCreationOptionService, times(1)).getBackgrounds();
    }

    @Test
    void getSkills_Success() throws Exception {
        
        List<SkillApiDto> skills = Arrays.asList(
                new SkillApiDto("Acrobatics"),
                new SkillApiDto("Arcana")
        );
        when(characterCreationOptionService.getSkills()).thenReturn(skills);

        
        mockMvc.perform(get("/character-creation-options/skills")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].name", is("Acrobatics")))
                .andExpect(jsonPath("$[1].name", is("Arcana")));

        verify(characterCreationOptionService, times(1)).getSkills();
    }

    @Test
    void getSpells_Success() throws Exception {
        
        List<SpellApiDto> spells = Arrays.asList(
                new SpellApiDto("Fireball", "3", "Evocation", "A bright streak flashes from your finger to a point you choose", "8d6", "", "", "", "", "", "", "", "", "", "", "", "", ""),
                new SpellApiDto("Magic Missile", "1", "Evocation", "You create darts of magical force", "1d4+1", "", "", "", "", "", "", "", "", "", "", "", "", "")
        );
        when(characterCreationOptionService.getSpells()).thenReturn(spells);

        
        mockMvc.perform(get("/character-creation-options/spells")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].name", is("Fireball")))
                .andExpect(jsonPath("$[1].name", is("Magic Missile")));

        verify(characterCreationOptionService, times(1)).getSpells();
    }

    @Test
    void getArmors_Success() throws Exception {
        
        List<ArmorApiDto> armors = Arrays.asList(
                new ArmorApiDto("Leather Armor","Leather Armor", ArmorCategory.LIGHT, 10, 0, false, BigDecimal.valueOf(
                        1.2), "", ""),
                new ArmorApiDto("Chain Mail","Leather Armor", ArmorCategory.HEAVY, 16, 3, true, BigDecimal.valueOf(
                        1.2), "", "")
        );
        when(characterCreationOptionService.getArmors()).thenReturn(armors);

        
        mockMvc.perform(get("/character-creation-options/armors")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].index", is("Leather Armor")))
                .andExpect(jsonPath("$[1].index", is("Chain Mail")));

        verify(characterCreationOptionService, times(1)).getArmors();
    }

    @Test
    void getWeapons_Success() throws Exception {
        
        List<WeaponApiDto> weapons = Arrays.asList(
                new WeaponApiDto("Longsword", "Martial", "1d8", "Slashing", "", "", BigDecimal.valueOf(1.7), ""),
                new WeaponApiDto("Shortbow", "Simple", "1d6", "Piercing", "", "", BigDecimal.valueOf(1.7), "")
        );
        when(characterCreationOptionService.getWeapons()).thenReturn(weapons);

        
        mockMvc.perform(get("/character-creation-options/weapons")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].index", is("Longsword")))
                .andExpect(jsonPath("$[1].index", is("Shortbow")));

        verify(characterCreationOptionService, times(1)).getWeapons();
    }

    @Test
    void getRaces_EmptyList() throws Exception {
        
        when(characterCreationOptionService.getRaces()).thenReturn(new ArrayList<>());

        
        mockMvc.perform(get("/character-creation-options/races")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(0)));

        verify(characterCreationOptionService, times(1)).getRaces();
    }

    @Test
    void getClasses_EmptyList() throws Exception {
        
        when(characterCreationOptionService.getClasses()).thenReturn(new ArrayList<>());

        
        mockMvc.perform(get("/character-creation-options/classes")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(0)));

        verify(characterCreationOptionService, times(1)).getClasses();
    }

    @Test
    void getBackgrounds_EmptyList() throws Exception {
        
        when(characterCreationOptionService.getBackgrounds()).thenReturn(new ArrayList<>());

        
        mockMvc.perform(get("/character-creation-options/backgrounds")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(0)));

        verify(characterCreationOptionService, times(1)).getBackgrounds();
    }

    @Test
    void getSkills_EmptyList() throws Exception {
        
        when(characterCreationOptionService.getSkills()).thenReturn(new ArrayList<>());

        
        mockMvc.perform(get("/character-creation-options/skills")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(0)));

        verify(characterCreationOptionService, times(1)).getSkills();
    }

    @Test
    void getSpells_EmptyList() throws Exception {
        
        when(characterCreationOptionService.getSpells()).thenReturn(new ArrayList<>());

        
        mockMvc.perform(get("/character-creation-options/spells")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(0)));

        verify(characterCreationOptionService, times(1)).getSpells();
    }

    @Test
    void getArmors_EmptyList() throws Exception {
        
        when(characterCreationOptionService.getArmors()).thenReturn(new ArrayList<>());

        
        mockMvc.perform(get("/character-creation-options/armors")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(0)));

        verify(characterCreationOptionService, times(1)).getArmors();
    }

    @Test
    void getWeapons_EmptyList() throws Exception {
        
        when(characterCreationOptionService.getWeapons()).thenReturn(new ArrayList<>());

        
        mockMvc.perform(get("/character-creation-options/weapons")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(0)));

        verify(characterCreationOptionService, times(1)).getWeapons();
    }
}
