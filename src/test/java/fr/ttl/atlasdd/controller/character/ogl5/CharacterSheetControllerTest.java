package fr.ttl.atlasdd.controller.character.ogl5;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.ttl.atlasdd.apidto.character.ogl5.*;
import fr.ttl.atlasdd.apidto.user.UserLightApiDto;
import fr.ttl.atlasdd.exception.GlobalExceptionHandler;
import fr.ttl.atlasdd.exception.character.CharacterPreparedSpellNotFoundException;
import fr.ttl.atlasdd.exception.character.CharacterSkillNotFoundException;
import fr.ttl.atlasdd.exception.character.ogl5.notfound.*;
import fr.ttl.atlasdd.exception.character.ogl5.savingerror.Ogl5CharacterSavingErrorException;
import fr.ttl.atlasdd.exception.user.UserNotFoundException;
import fr.ttl.atlasdd.service.character.ogl5.CharacterSheetService;
import fr.ttl.atlasdd.utils.character.Alignment;
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

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CharacterSheetControllerTest {

    private MockMvc mockMvc;

    @Mock
    private CharacterSheetService characterSheetService;

    @InjectMocks
    private CharacterSheetController characterSheetController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(characterSheetController)
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
    private final Long RACE_ID = 1L;
    private final Long BACKGROUND_ID = 1L;
    private final Long CLASS_ID = 1L;
    private final List<Long> SKILLS = List.of(3L, 7L, 10L, 15L);
    private final List<Long> SPELLS = List.of(95L, 104L, 370L, 26L);
    private final List<Long> WEAPONS = List.of(19L, 20L);
    private final Long ARMOR_ID = 12L;

    private final UserLightApiDto USER_LIGHT_API_DTO = new UserLightApiDto();
    private final RaceApiDto RACE_API_DTO = new RaceApiDto();
    private final BackgroundApiDto BACKGROUND_API_DTO = new BackgroundApiDto();
    private final ClassApiDto CLASS_API_DTO = new ClassApiDto();
    private final ArmorApiDto ARMOR_API_DTO = new ArmorApiDto();
    private final ShieldType SHIELD_TYPE = ShieldType.NORMAL;
    private final Alignment ALIGNMENT_TYPE = Alignment.LOYAL_BON;
    private final CharacterStatus STATUS_TYPE = CharacterStatus.VIVANT;
    private final List<SkillApiDto> SKILLS_API_DTO = new ArrayList<>();
    private final List<SpellApiDto> SPELLS_API_DTO = new ArrayList<>();
    private final List<WeaponApiDto> WEAPONS_API_DTO = new ArrayList<>();

    private final List<CharacterSheetApiDto> characterSheetApiDtos = Collections.emptyList();

    private final CharacterSheetCreateRequestApiDto characterSheetCreateRequestApiDto = new CharacterSheetCreateRequestApiDto(
            NAME,
            LEVEL,
            EXPERIENCE_POINTS,
            ARMOR_CLASS,
            INITIATIVE,
            INSPIRATION,
            HIT_POINTS,
            MAX_HIT_POINTS,
            BONUS_HIT_POINTS,
            SPEED,
            PASSIVE_PERCEPTION,
            SHIELD,
            TWO_WEAPONS,
            ALIGNMENT,
            STRENGTH,
            DEXTERITY,
            CONSTITUTION,
            INTELLIGENCE,
            WISDOM,
            CHARISMA,
            STATUS,
            USER_ID,
            RACE_ID,
            BACKGROUND_ID,
            CLASS_ID,
            SKILLS,
            SPELLS,
            WEAPONS,
            ARMOR_ID
    );

    private final CharacterSheetUpdateRequestApiDto characterSheetUpdateRequestApiDto = new CharacterSheetUpdateRequestApiDto(
            CHARACTER_SHEET_ID,
            NAME,
            LEVEL,
            EXPERIENCE_POINTS,
            ARMOR_CLASS,
            INITIATIVE,
            INSPIRATION,
            HIT_POINTS,
            MAX_HIT_POINTS,
            BONUS_HIT_POINTS,
            SPEED,
            PASSIVE_PERCEPTION,
            SHIELD,
            TWO_WEAPONS,
            ALIGNMENT,
            STRENGTH,
            DEXTERITY,
            CONSTITUTION,
            INTELLIGENCE,
            WISDOM,
            CHARISMA,
            STATUS,
            USER_ID,
            RACE_ID,
            BACKGROUND_ID,
            CLASS_ID,
            SKILLS,
            SPELLS,
            WEAPONS,
            ARMOR_ID
    );

    private final CharacterSheetApiDto characterSheetApiDto = new CharacterSheetApiDto(
            NAME,
            LEVEL,
            EXPERIENCE_POINTS,
            ARMOR_CLASS,
            INITIATIVE,
            INSPIRATION,
            HIT_POINTS,
            MAX_HIT_POINTS,
            BONUS_HIT_POINTS,
            SPEED,
            PASSIVE_PERCEPTION,
            SHIELD_TYPE,
            TWO_WEAPONS,
            ALIGNMENT_TYPE,
            STRENGTH,
            DEXTERITY,
            CONSTITUTION,
            INTELLIGENCE,
            WISDOM,
            CHARISMA,
            STATUS_TYPE,
            USER_LIGHT_API_DTO,
            RACE_API_DTO,
            BACKGROUND_API_DTO,
            CLASS_API_DTO,
            SKILLS_API_DTO,
            SPELLS_API_DTO,
            WEAPONS_API_DTO,
            ARMOR_API_DTO
    );

    @Test
    void createCharacterSheet_Success() throws Exception {

        when(characterSheetService.createCharacterSheet(characterSheetCreateRequestApiDto)).thenReturn(characterSheetApiDto);

        mockMvc.perform(post("/ogl5/characters")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(characterSheetCreateRequestApiDto)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value(NAME))
                .andExpect(jsonPath("$.level").value(LEVEL))
                .andExpect(jsonPath("$.experience").value(EXPERIENCE_POINTS))
                .andExpect(jsonPath("$.armorClass").value(ARMOR_CLASS))
                .andExpect(jsonPath("$.initiative").value(INITIATIVE))
                .andExpect(jsonPath("$.inspiration").value(INSPIRATION))
                .andExpect(jsonPath("$.hitPoints").value(HIT_POINTS))
                .andExpect(jsonPath("$.maxHitPoints").value(MAX_HIT_POINTS))
                .andExpect(jsonPath("$.bonusHitPoints").value(BONUS_HIT_POINTS))
                .andExpect(jsonPath("$.speed").value(SPEED))
                .andExpect(jsonPath("$.passivePerception").value(PASSIVE_PERCEPTION))
                .andExpect(jsonPath("$.shield").value(SHIELD_TYPE.toString()))
                .andExpect(jsonPath("$.twoWeaponsFighting").value(TWO_WEAPONS))
                .andExpect(jsonPath("$.alignment").value(ALIGNMENT_TYPE.toString()))
                .andExpect(jsonPath("$.strength").value(STRENGTH))
                .andExpect(jsonPath("$.dexterity").value(DEXTERITY))
                .andExpect(jsonPath("$.constitution").value(CONSTITUTION))
                .andExpect(jsonPath("$.intelligence").value(INTELLIGENCE))
                .andExpect(jsonPath("$.wisdom").value(WISDOM))
                .andExpect(jsonPath("$.charisma").value(CHARISMA))
                .andExpect(jsonPath("$.status").value(STATUS_TYPE.toString()))
                .andExpect(jsonPath("$.owner").value(USER_LIGHT_API_DTO))
                .andExpect(jsonPath("$.race").value(RACE_API_DTO))
                .andExpect(jsonPath("$.background").value(BACKGROUND_API_DTO))
                .andExpect(jsonPath("$.classe").value(CLASS_API_DTO))
                .andExpect(jsonPath("$.armor").value(ARMOR_API_DTO));
    }

    @Test
    void createCharacterSheet_UserNotFound() throws Exception {

        when(characterSheetService.createCharacterSheet(characterSheetCreateRequestApiDto))
                .thenThrow(new UserNotFoundException(ExceptionMessage.USER_NOT_FOUND.getMessage()));

        mockMvc.perform(post("/ogl5/characters")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(characterSheetCreateRequestApiDto)))
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.message").value(ExceptionMessage.USER_NOT_FOUND.getMessage()));
    }

    @Test
    void createCharacterSheet_RaceNotFound() throws Exception {

        when(characterSheetService.createCharacterSheet(characterSheetCreateRequestApiDto))
                .thenThrow(new Ogl5RaceNotFoundException(ExceptionMessage.RACE_NOT_FOUND.getMessage()));

        mockMvc.perform(post("/ogl5/characters")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(characterSheetCreateRequestApiDto)))
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.message").value(ExceptionMessage.RACE_NOT_FOUND.getMessage()));
    }

    @Test
    void createCharacterSheet_BackgroundNotFound() throws Exception {

        when(characterSheetService.createCharacterSheet(characterSheetCreateRequestApiDto))
                .thenThrow(new Ogl5BackgroundNotFoundException(ExceptionMessage.BACKGROUND_NOT_FOUND.getMessage()));

        mockMvc.perform(post("/ogl5/characters")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(characterSheetCreateRequestApiDto)))
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.message").value(ExceptionMessage.BACKGROUND_NOT_FOUND.getMessage()));
    }

    @Test
    void createCharacterSheet_ClassNotFound() throws Exception {

        when(characterSheetService.createCharacterSheet(characterSheetCreateRequestApiDto))
                .thenThrow(new Ogl5ClassNotFoundException(ExceptionMessage.CLASS_NOT_FOUND.getMessage()));

        mockMvc.perform(post("/ogl5/characters")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(characterSheetCreateRequestApiDto)))
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.message").value(ExceptionMessage.CLASS_NOT_FOUND.getMessage()));
    }

    @Test
    void createCharacterSheet_SkillsNotFound() throws Exception {

        when(characterSheetService.createCharacterSheet(characterSheetCreateRequestApiDto))
                .thenThrow(new CharacterSkillNotFoundException(ExceptionMessage.SKILL_RETRIEVE_ERROR.getMessage()));

        mockMvc.perform(post("/ogl5/characters")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(characterSheetCreateRequestApiDto)))
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.message").value(ExceptionMessage.SKILL_RETRIEVE_ERROR.getMessage()));
    }

    @Test
    void createCharacterSheet_SpellsNotFound() throws Exception {

        when(characterSheetService.createCharacterSheet(characterSheetCreateRequestApiDto))
                .thenThrow(new CharacterPreparedSpellNotFoundException(ExceptionMessage.SPELL_RETRIEVE_ERROR.getMessage()));

        mockMvc.perform(post("/ogl5/characters")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(characterSheetCreateRequestApiDto)))
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.message").value(ExceptionMessage.SPELL_RETRIEVE_ERROR.getMessage()));
    }

    @Test
    void createCharacterSheet_WeaponsNotFound() throws Exception {

        when(characterSheetService.createCharacterSheet(characterSheetCreateRequestApiDto))
                .thenThrow(new Ogl5WeaponNotFoundException(ExceptionMessage.WEAPON_NOT_FOUND.getMessage()));

        mockMvc.perform(post("/ogl5/characters")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(characterSheetCreateRequestApiDto)))
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.message").value(ExceptionMessage.WEAPON_NOT_FOUND.getMessage()));
    }

    @Test
    void createCharacterSheet_ArmorNotFound() throws Exception {

        when(characterSheetService.createCharacterSheet(characterSheetCreateRequestApiDto))
                .thenThrow(new Ogl5ArmorNotFoundException(ExceptionMessage.ARMOR_NOT_FOUND.getMessage()));

        mockMvc.perform(post("/ogl5/characters")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(characterSheetCreateRequestApiDto)))
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.message").value(ExceptionMessage.ARMOR_NOT_FOUND.getMessage()));
    }

    @Test
    void createCharacterSheet_SavingError() throws Exception {

        when(characterSheetService.createCharacterSheet(characterSheetCreateRequestApiDto))
                .thenThrow(new Ogl5CharacterSavingErrorException(ExceptionMessage.CHARACTER_SAVE_ERROR.getMessage()));

        mockMvc.perform(post("/ogl5/characters")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(characterSheetCreateRequestApiDto)))
                .andExpect(status().isInternalServerError())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.message").value(ExceptionMessage.CHARACTER_SAVE_ERROR.getMessage()));
    }

    @Test
    void getCharacterSheetsById_Success() throws Exception {

        when(characterSheetService.getCharacterSheetById(CHARACTER_SHEET_ID)).thenReturn(characterSheetApiDto);

        mockMvc.perform(get("/ogl5/characters/{id}", CHARACTER_SHEET_ID)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value(NAME))
                .andExpect(jsonPath("$.level").value(LEVEL))
                .andExpect(jsonPath("$.experience").value(EXPERIENCE_POINTS))
                .andExpect(jsonPath("$.armorClass").value(ARMOR_CLASS))
                .andExpect(jsonPath("$.initiative").value(INITIATIVE))
                .andExpect(jsonPath("$.inspiration").value(INSPIRATION))
                .andExpect(jsonPath("$.hitPoints").value(HIT_POINTS))
                .andExpect(jsonPath("$.maxHitPoints").value(MAX_HIT_POINTS))
                .andExpect(jsonPath("$.bonusHitPoints").value(BONUS_HIT_POINTS))
                .andExpect(jsonPath("$.speed").value(SPEED))
                .andExpect(jsonPath("$.passivePerception").value(PASSIVE_PERCEPTION))
                .andExpect(jsonPath("$.shield").value(SHIELD_TYPE.toString()))
                .andExpect(jsonPath("$.twoWeaponsFighting").value(TWO_WEAPONS))
                .andExpect(jsonPath("$.alignment").value(ALIGNMENT_TYPE.toString()))
                .andExpect(jsonPath("$.strength").value(STRENGTH))
                .andExpect(jsonPath("$.dexterity").value(DEXTERITY))
                .andExpect(jsonPath("$.constitution").value(CONSTITUTION))
                .andExpect(jsonPath("$.intelligence").value(INTELLIGENCE))
                .andExpect(jsonPath("$.wisdom").value(WISDOM))
                .andExpect(jsonPath("$.charisma").value(CHARISMA))
                .andExpect(jsonPath("$.status").value(STATUS_TYPE.toString()))
                .andExpect(jsonPath("$.owner").value(USER_LIGHT_API_DTO))
                .andExpect(jsonPath("$.race").value(RACE_API_DTO))
                .andExpect(jsonPath("$.background").value(BACKGROUND_API_DTO))
                .andExpect(jsonPath("$.classe").value(CLASS_API_DTO))
                .andExpect(jsonPath("$.skills").value(SKILLS_API_DTO))
                .andExpect(jsonPath("$.preparedSpells").value(SPELLS_API_DTO))
                .andExpect(jsonPath("$.weapons").value(WEAPONS_API_DTO))
                .andExpect(jsonPath("$.armor").value(ARMOR_API_DTO));
    }

    @Test
    void getCharacterSheetsById_NotFound() throws Exception {

            when(characterSheetService.getCharacterSheetById(CHARACTER_SHEET_ID))
                    .thenThrow(new Ogl5CharacterNotFoundException(ExceptionMessage.CHARACTER_NOT_FOUND.getMessage()));

            mockMvc.perform(get("/ogl5/characters/{id}", CHARACTER_SHEET_ID)
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isNotFound())
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                    .andExpect(jsonPath("$.message").value(ExceptionMessage.CHARACTER_NOT_FOUND.getMessage()));

    }

    @Test
    void getCharacterSheetsByUserId_Success() throws Exception {

        when(characterSheetService.getCharacterSheetsByUserId(USER_ID)).thenReturn(characterSheetApiDtos);

        mockMvc.perform(get("/ogl5/characters/users/{userId}", USER_ID)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isEmpty());
    }

    @Test
    void getCharacterSheetsByUserId_UserNotFound() throws Exception {

        when(characterSheetService.getCharacterSheetsByUserId(USER_ID))
                .thenThrow(new UserNotFoundException(ExceptionMessage.USER_NOT_FOUND.getMessage()));

        mockMvc.perform(get("/ogl5/characters/users/{userId}", USER_ID)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.message").value(ExceptionMessage.USER_NOT_FOUND.getMessage()));
    }

    @Test
    void updateCharacterSheet_Success() throws Exception {

        when(characterSheetService.updateCharacterSheet(CHARACTER_SHEET_ID ,characterSheetUpdateRequestApiDto)).thenReturn(characterSheetApiDto);

        mockMvc.perform(patch("/ogl5/characters/{id}", CHARACTER_SHEET_ID)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(characterSheetUpdateRequestApiDto)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value(NAME))
                .andExpect(jsonPath("$.level").value(LEVEL))
                .andExpect(jsonPath("$.experience").value(EXPERIENCE_POINTS))
                .andExpect(jsonPath("$.armorClass").value(ARMOR_CLASS))
                .andExpect(jsonPath("$.initiative").value(INITIATIVE))
                .andExpect(jsonPath("$.inspiration").value(INSPIRATION))
                .andExpect(jsonPath("$.hitPoints").value(HIT_POINTS))
                .andExpect(jsonPath("$.maxHitPoints").value(MAX_HIT_POINTS))
                .andExpect(jsonPath("$.bonusHitPoints").value(BONUS_HIT_POINTS))
                .andExpect(jsonPath("$.speed").value(SPEED))
                .andExpect(jsonPath("$.passivePerception").value(PASSIVE_PERCEPTION))
                .andExpect(jsonPath("$.shield").value(SHIELD_TYPE.toString()))
                .andExpect(jsonPath("$.twoWeaponsFighting").value(TWO_WEAPONS))
                .andExpect(jsonPath("$.alignment").value(ALIGNMENT_TYPE.toString()))
                .andExpect(jsonPath("$.strength").value(STRENGTH))
                .andExpect(jsonPath("$.dexterity").value(DEXTERITY))
                .andExpect(jsonPath("$.constitution").value(CONSTITUTION))
                .andExpect(jsonPath("$.intelligence").value(INTELLIGENCE))
                .andExpect(jsonPath("$.wisdom").value(WISDOM))
                .andExpect(jsonPath("$.charisma").value(CHARISMA))
                .andExpect(jsonPath("$.status").value(STATUS_TYPE.toString()))
                .andExpect(jsonPath("$.owner").value(USER_LIGHT_API_DTO))
                .andExpect(jsonPath("$.race").value(RACE_API_DTO))
                .andExpect(jsonPath("$.background").value(BACKGROUND_API_DTO))
                .andExpect(jsonPath("$.classe").value(CLASS_API_DTO))
                .andExpect(jsonPath("$.armor").value(ARMOR_API_DTO));
    }

    @Test
    void updateCharacterSheet_CharacterNotFound() throws Exception {

        when(characterSheetService.updateCharacterSheet(CHARACTER_SHEET_ID ,characterSheetUpdateRequestApiDto))
                .thenThrow(new Ogl5CharacterNotFoundException(ExceptionMessage.CHARACTER_NOT_FOUND.getMessage()));

        mockMvc.perform(patch("/ogl5/characters/{id}", CHARACTER_SHEET_ID)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(characterSheetUpdateRequestApiDto)))
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.message").value(ExceptionMessage.CHARACTER_NOT_FOUND.getMessage()));
    }

    @Test
    void updateCharacterSheet_SkillsNotFound() throws Exception {

        when(characterSheetService.updateCharacterSheet(CHARACTER_SHEET_ID ,characterSheetUpdateRequestApiDto))
                .thenThrow(new CharacterSkillNotFoundException(ExceptionMessage.SKILL_RETRIEVE_ERROR.getMessage()));

        mockMvc.perform(patch("/ogl5/characters/{id}", CHARACTER_SHEET_ID)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(characterSheetUpdateRequestApiDto)))
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.message").value(ExceptionMessage.SKILL_RETRIEVE_ERROR.getMessage()));
    }

    @Test
    void updateCharacterSheet_SpellsNotFound() throws Exception {

        when(characterSheetService.updateCharacterSheet(CHARACTER_SHEET_ID ,characterSheetUpdateRequestApiDto))
                .thenThrow(new CharacterPreparedSpellNotFoundException(ExceptionMessage.SPELL_RETRIEVE_ERROR.getMessage()));

        mockMvc.perform(patch("/ogl5/characters/{id}", CHARACTER_SHEET_ID)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(characterSheetUpdateRequestApiDto)))
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.message").value(ExceptionMessage.SPELL_RETRIEVE_ERROR.getMessage()));
    }

    @Test
    void updateCharacterSheet_SavingError() throws Exception {

        when(characterSheetService.updateCharacterSheet(CHARACTER_SHEET_ID ,characterSheetUpdateRequestApiDto))
                .thenThrow(new Ogl5CharacterSavingErrorException(ExceptionMessage.CHARACTER_UPDATE_ERROR.getMessage()));

        mockMvc.perform(patch("/ogl5/characters/{id}", CHARACTER_SHEET_ID)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(characterSheetUpdateRequestApiDto)))
                .andExpect(status().isInternalServerError())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.message").value(ExceptionMessage.CHARACTER_UPDATE_ERROR.getMessage()));
    }

    private String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
