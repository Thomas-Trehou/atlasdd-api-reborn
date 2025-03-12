package fr.ttl.atlasdd.controller.character;

import fr.ttl.atlasdd.apidto.character.*;
import fr.ttl.atlasdd.apidto.character.ogl5.ClassApiDto;
import fr.ttl.atlasdd.apidto.character.ogl5.RaceApiDto;
import fr.ttl.atlasdd.service.character.CharacterCreationOptionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/character-creation-options")
@RequiredArgsConstructor
public class CharacterCreationOptionController {

    private final CharacterCreationOptionService characterCreationOptionService;

    @Operation(summary = "Get all races")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Races found, returns the races"),
            @ApiResponse(responseCode = "500", description = "Something goes wrong at list creation", content = @Content)
    })
    @GetMapping("/races")
    public List<RaceApiDto> getRaces() {
        return characterCreationOptionService.getRaces();
    }

    @Operation(summary = "Get all classes")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Classes found, returns the classes"),
            @ApiResponse(responseCode = "500", description = "Something goes wrong at list creation", content = @Content)
    })
    @GetMapping("/classes")
    public List<ClassApiDto> getClasses() {
        return characterCreationOptionService.getClasses();
    }

    @Operation(summary = "Get all backgrounds")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Backgrounds found, returns the backgrounds"),
            @ApiResponse(responseCode = "500", description = "Something goes wrong at list creation", content = @Content)
    })
    @GetMapping("/backgrounds")
    public List<BackgroundApiDto> getBackgrounds() {
        return characterCreationOptionService.getBackgrounds();
    }

    @Operation(summary = "Get all skills")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Skills found, returns the skills"),
            @ApiResponse(responseCode = "500", description = "Something goes wrong at list creation", content = @Content)
    })
    @GetMapping("/skills")
    public List<SkillApiDto> getSkills() {
        return characterCreationOptionService.getSkills();
    }

    @Operation(summary = "Get all spells")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Spells found, returns the spells"),
            @ApiResponse(responseCode = "500", description = "Something goes wrong at list creation", content = @Content)
    })
    @GetMapping("/spells")
    public List<SpellApiDto> getSpells() {
        return characterCreationOptionService.getSpells();
    }

    @Operation(summary = "Get all armors")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Armors found, returns the armors"),
            @ApiResponse(responseCode = "500", description = "Something goes wrong at list creation", content = @Content)
    })
    @GetMapping("/armors")
    public List<ArmorApiDto> getArmors() {
        return characterCreationOptionService.getArmors();
    }

    @Operation(summary = "Get all weapons")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Weapons found, returns the weapons"),
            @ApiResponse(responseCode = "500", description = "Something goes wrong at list creation", content = @Content)
    })
    @GetMapping("/weapons")
    public List<WeaponApiDto> getWeapons() {
        return characterCreationOptionService.getWeapons();
    }

}
