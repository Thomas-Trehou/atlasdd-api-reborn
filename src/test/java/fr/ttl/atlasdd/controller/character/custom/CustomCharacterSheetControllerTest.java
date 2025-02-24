package fr.ttl.atlasdd.controller.character.custom;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.ttl.atlasdd.apidto.character.custom.*;
import fr.ttl.atlasdd.apidto.user.UserLightApiDto;
import fr.ttl.atlasdd.exception.GlobalExceptionHandler;
import fr.ttl.atlasdd.exception.character.CharacterSkillNotFoundException;
import fr.ttl.atlasdd.exception.character.custom.savingerror.*;
import fr.ttl.atlasdd.exception.user.UserNotFoundException;
import fr.ttl.atlasdd.service.character.custom.CustomCharacterSheetService;
import fr.ttl.atlasdd.utils.character.Alignment;
import fr.ttl.atlasdd.utils.character.ArmorCategory;
import fr.ttl.atlasdd.utils.character.CharacterStatus;
import fr.ttl.atlasdd.utils.character.ShieldType;
import fr.ttl.atlasdd.utils.exception.ExceptionMessage;
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
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class CustomCharacterSheetControllerTest {

    private MockMvc mockMvc;

    @Mock
    private CustomCharacterSheetService customCharacterSheetService;

    @InjectMocks
    private CustomCharacterSheetController customCharacterSheetController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(customCharacterSheetController)
                .setControllerAdvice(new GlobalExceptionHandler())
                .build();
    }

    private final Long CHARACTER_SHEET_ID = 1L;
    private final String NAME = "CharacterName";
    private final int LEVEL = 1;
    private final int EXPERIENCE_POINTS = 0;
    private final int ARMOR_CLASS = 15;
    private final int INITIATIVE = 3;
    private final int INSPIRATION = 1;
    private final int HIT_POINTS = 15;
    private final int MAX_HIT_POINTS = 20;
    private final int BONUS_HIT_POINTS = 3;
    private final int SPEED = 9;
    private final int PASSIVE_PERCEPTION = 14;
    private final String SHIELD = "NORMAL";
    private final boolean TWO_WEAPONS = false;
    private final String ALIGNMENT = "LOYAL_BON";
    private final int STRENGTH = 14;
    private final int DEXTERITY = 16;
    private final int CONSTITUTION = 16;
    private final int INTELLIGENCE = 10;
    private final int WISDOM = 8;
    private final int CHARISMA = 14;
    private final String STATUS = "VIVANT";
    private final Long USER_ID = 1L;
    private final CustomRaceApiDto RACE = new CustomRaceApiDto("Human", "30ft", "Common", "None");
    private final CustomBackgroundApiDto BACKGROUND = new CustomBackgroundApiDto("Noble", "None", "None", "None");
    private final CustomClassApiDto CLASS = new CustomClassApiDto("Barbarian", "d12", 12, "None");
    private final List<Long> SKILLS = List.of(3L, 7L, 10L, 15L);
    private final List<Long> SPELLS = List.of(95L, 104L, 370L, 26L);
    private final List<CustomWeaponApiDto> WEAPONS = List.of(
            new CustomWeaponApiDto("Sword", "Epée", "CaC", "2PA", "1d8","Slashing", new BigDecimal("1.5"), "None"),
            new CustomWeaponApiDto("Bow", "Arc", "30ft", "1PA", "1d6","Piercing", new BigDecimal("1.5"), "None")
    );
    private final CustomArmorApiDto ARMOR = new CustomArmorApiDto("Leather", "Cuir", ArmorCategory.LIGHT, 12, 0, false, new BigDecimal("5.5"), "3PA", "None");

    private final UserLightApiDto USER = new UserLightApiDto();
    private final ShieldType SHIELD_TYPE = ShieldType.NORMAL;
    private final Alignment ALIGNMENT_TYPE = Alignment.LOYAL_BON;
    private final CharacterStatus STATUS_TYPE = CharacterStatus.VIVANT;
    private final List<CustomSkillApiDto> SKILLS_API_DTO = new ArrayList<>();
    private final List<CustomSpellApiDto> SPELLS_API_DTO = new ArrayList<>();

    private final List<CustomCharacterSheetApiDto> CUSTOM_CHARACTER_SHEET_API_DTO_LIST = Collections.emptyList();

    private final CustomCharacterSheetCreateRequestApiDto CUSTOM_CHARACTER_SHEET_CREATE_REQUEST_API_DTO = new CustomCharacterSheetCreateRequestApiDto(
            NAME, LEVEL, EXPERIENCE_POINTS, ARMOR_CLASS, INITIATIVE, INSPIRATION, HIT_POINTS, MAX_HIT_POINTS, BONUS_HIT_POINTS, SPEED, PASSIVE_PERCEPTION,
            SHIELD_TYPE, TWO_WEAPONS, ALIGNMENT_TYPE, STRENGTH, DEXTERITY, CONSTITUTION, INTELLIGENCE, WISDOM, CHARISMA, STATUS_TYPE, USER_ID, RACE, BACKGROUND, CLASS, SKILLS, SPELLS, WEAPONS, ARMOR);

    private final CustomCharacterSheetUpdateRequestApiDto CUSTOM_CHARACTER_SHEET_UPDATE_REQUEST_API_DTO = new CustomCharacterSheetUpdateRequestApiDto(
            CHARACTER_SHEET_ID, NAME, LEVEL, EXPERIENCE_POINTS, ARMOR_CLASS, INITIATIVE, INSPIRATION, HIT_POINTS, MAX_HIT_POINTS, BONUS_HIT_POINTS, SPEED, PASSIVE_PERCEPTION,
            SHIELD_TYPE.toString(), TWO_WEAPONS, ALIGNMENT_TYPE.toString(), STRENGTH, DEXTERITY, CONSTITUTION, INTELLIGENCE, WISDOM, CHARISMA, STATUS_TYPE, USER_ID, RACE, BACKGROUND, CLASS, SKILLS, SPELLS, WEAPONS, ARMOR);

    private final CustomCharacterSheetApiDto CUSTOM_CHARACTER_SHEET_API_DTO = new CustomCharacterSheetApiDto(
            NAME, LEVEL, EXPERIENCE_POINTS, ARMOR_CLASS, INITIATIVE, INSPIRATION, HIT_POINTS, MAX_HIT_POINTS, BONUS_HIT_POINTS, SPEED, PASSIVE_PERCEPTION,
            SHIELD_TYPE, TWO_WEAPONS, ALIGNMENT_TYPE, STRENGTH, DEXTERITY, CONSTITUTION, INTELLIGENCE, WISDOM, CHARISMA, STATUS_TYPE, USER, RACE, BACKGROUND, CLASS, SKILLS_API_DTO, SPELLS_API_DTO, WEAPONS, ARMOR);

    @Test
    void createCustomCharacterSheet_Success() throws Exception {
        when(customCharacterSheetService.createCharacterSheet(CUSTOM_CHARACTER_SHEET_CREATE_REQUEST_API_DTO)).thenReturn(CUSTOM_CHARACTER_SHEET_API_DTO);

        mockMvc.perform(post("/custom/characters")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(CUSTOM_CHARACTER_SHEET_CREATE_REQUEST_API_DTO)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(asJsonString(CUSTOM_CHARACTER_SHEET_API_DTO)));
    }

    @Test
    void createCustomCharacterSheet_UserNotFound() throws Exception {
        when(customCharacterSheetService.createCharacterSheet(CUSTOM_CHARACTER_SHEET_CREATE_REQUEST_API_DTO)).thenThrow(new UserNotFoundException(ExceptionMessage.USER_NOT_FOUND.getMessage()));

        mockMvc.perform(post("/custom/characters")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(CUSTOM_CHARACTER_SHEET_CREATE_REQUEST_API_DTO)))
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.message").value(ExceptionMessage.USER_NOT_FOUND.getMessage()));
    }

    @Test
    void createCustomCharacterSheet_SkillsNotFound() throws Exception {
        when(customCharacterSheetService.createCharacterSheet(CUSTOM_CHARACTER_SHEET_CREATE_REQUEST_API_DTO)).thenThrow(new CharacterSkillNotFoundException(ExceptionMessage.SKILL_RETRIEVE_ERROR.getMessage()));

        mockMvc.perform(post("/custom/characters")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(CUSTOM_CHARACTER_SHEET_CREATE_REQUEST_API_DTO)))
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.message").value(ExceptionMessage.SKILL_RETRIEVE_ERROR.getMessage()));
    }

    @Test
    void createCustomCharacterSheet_SpellsNotFound() throws Exception {
        when(customCharacterSheetService.createCharacterSheet(CUSTOM_CHARACTER_SHEET_CREATE_REQUEST_API_DTO)).thenThrow(new CharacterSkillNotFoundException(ExceptionMessage.SPELL_RETRIEVE_ERROR.getMessage()));

        mockMvc.perform(post("/custom/characters")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(CUSTOM_CHARACTER_SHEET_CREATE_REQUEST_API_DTO)))
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.message").value(ExceptionMessage.SPELL_RETRIEVE_ERROR.getMessage()));
    }

    @Test
    void createCustomCharacterSheet_RaceSaveError() throws Exception {
        when(customCharacterSheetService.createCharacterSheet(CUSTOM_CHARACTER_SHEET_CREATE_REQUEST_API_DTO)).thenThrow(new CustomRaceSavingErrorException(ExceptionMessage.RACE_SAVE_ERROR.getMessage()));

        mockMvc.perform(post("/custom/characters")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(CUSTOM_CHARACTER_SHEET_CREATE_REQUEST_API_DTO)))
                .andExpect(status().isInternalServerError())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.message").value(ExceptionMessage.RACE_SAVE_ERROR.getMessage()));
    }

    @Test
    void createCustomCharacterSheet_BackgroundSaveError() throws Exception {
        when(customCharacterSheetService.createCharacterSheet(CUSTOM_CHARACTER_SHEET_CREATE_REQUEST_API_DTO)).thenThrow(new CustomBackgroundSavingErrorException(ExceptionMessage.BACKGROUND_SAVE_ERROR.getMessage()));

        mockMvc.perform(post("/custom/characters")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(CUSTOM_CHARACTER_SHEET_CREATE_REQUEST_API_DTO)))
                .andExpect(status().isInternalServerError())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.message").value(ExceptionMessage.BACKGROUND_SAVE_ERROR.getMessage()));
    }

    @Test
    void createCustomCharacterSheet_ClassSaveError() throws Exception {
        when(customCharacterSheetService.createCharacterSheet(CUSTOM_CHARACTER_SHEET_CREATE_REQUEST_API_DTO)).thenThrow(new CustomClassSavingErrorException(ExceptionMessage.CLASS_SAVE_ERROR.getMessage()));

        mockMvc.perform(post("/custom/characters")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(CUSTOM_CHARACTER_SHEET_CREATE_REQUEST_API_DTO)))
                .andExpect(status().isInternalServerError())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.message").value(ExceptionMessage.CLASS_SAVE_ERROR.getMessage()));
    }

    @Test
    void createCustomCharacterSheet_WeaponsSaveError() throws Exception {
        when(customCharacterSheetService.createCharacterSheet(CUSTOM_CHARACTER_SHEET_CREATE_REQUEST_API_DTO)).thenThrow(new CustomWeaponSavingErrorException(ExceptionMessage.WEAPON_SAVE_ERROR.getMessage()));

        mockMvc.perform(post("/custom/characters")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(CUSTOM_CHARACTER_SHEET_CREATE_REQUEST_API_DTO)))
                .andExpect(status().isInternalServerError())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.message").value(ExceptionMessage.WEAPON_SAVE_ERROR.getMessage()));
    }

    @Test
    void createCustomCharacterSheet_ArmorSaveError() throws Exception {
        when(customCharacterSheetService.createCharacterSheet(CUSTOM_CHARACTER_SHEET_CREATE_REQUEST_API_DTO)).thenThrow(new CustomArmorSavingErrorException(ExceptionMessage.ARMOR_SAVE_ERROR.getMessage()));

        mockMvc.perform(post("/custom/characters")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(CUSTOM_CHARACTER_SHEET_CREATE_REQUEST_API_DTO)))
                .andExpect(status().isInternalServerError())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.message").value(ExceptionMessage.ARMOR_SAVE_ERROR.getMessage()));
    }

    private String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
