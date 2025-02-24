package fr.ttl.atlasdd.controller.character.ogl5;

import fr.ttl.atlasdd.apidto.character.ogl5.CharacterSheetApiDto;
import fr.ttl.atlasdd.apidto.character.ogl5.CharacterSheetCreateRequestApiDto;
import fr.ttl.atlasdd.apidto.character.ogl5.CharacterSheetUpdateRequestApiDto;
import fr.ttl.atlasdd.service.character.ogl5.CharacterSheetService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ogl5/characters")
public class CharacterSheetController {

    private final CharacterSheetService characterSheetService;

    public CharacterSheetController(CharacterSheetService characterSheetService) {
        this.characterSheetService = characterSheetService;
    }

    @Operation(summary = "Create an Ogl5 character sheet")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ogl5 character sheet created, returns the created character sheet"),
            @ApiResponse(responseCode = "404", description =
                    "User not found / " +
                    "Race not found / " +
                    "Background not found / " +
                    "Class not found / " +
                    "Armor not found / " +
                    "Error when trying to retrieve skills / " +
                    "Error when trying to retrieve spells / " +
                    "Error when trying to retrieve weapons / " +
                    "Ogl5 character not found", content = @Content),
            @ApiResponse(responseCode = "500", description =
                    "Error at character sheet saving", content = @Content)
    })
    @PostMapping
    public CharacterSheetApiDto createCharacterSheet(
            @Parameter(description = "Character sheet to create", required = true)
            @RequestBody CharacterSheetCreateRequestApiDto characterSheetCreateRequestApiDto
    ) {
        return characterSheetService.createCharacterSheet(characterSheetCreateRequestApiDto);
    }

    @Operation(summary = "Get an Ogl5 character sheet by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ogl5 character sheet found, returns the character sheet"),
            @ApiResponse(responseCode = "404", description = "Ogl5 character sheet not found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Error at character sheet retrieval", content = @Content)
    })
    @GetMapping("/{id}")
    public CharacterSheetApiDto getCharacterSheet(
            @Parameter(description = "ID of the Ogl5 character sheet", required = true)
            @PathVariable Long id
    ) {
        return characterSheetService.getCharacterSheetById(id);
    }

    @Operation(summary = "Get all Ogl5 character sheets by user ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ogl5 character sheets found, returns the character sheets"),
            @ApiResponse(responseCode = "500", description = "Something goes wrong at list creation", content = @Content)
    })
    @GetMapping("/users/{userId}")
    public List<CharacterSheetApiDto> getCharacterSheetsByUserId(
            @Parameter(description = "ID of the user", required = true)
            @PathVariable Long userId
    ) {
        return characterSheetService.getCharacterSheetsByUserId(userId);
    }

    @Operation(summary = "Update an Ogl5 character sheet")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ogl5 character sheet updated, returns the updated character sheet"),
            @ApiResponse(responseCode = "404", description =
                    "User not found / " +
                    "Armor not found / " +
                    "Error when trying to retrieve skills / " +
                    "Error when trying to retrieve spells / " +
                    "Error when trying to retrieve weapons / " +
                    "Ogl5 character not found", content = @Content),
            @ApiResponse(responseCode = "500", description =
                    "Error at character sheet saving", content = @Content)
    })
    @PatchMapping("/{id}")
    public CharacterSheetApiDto updateCharacterSheet(
            @Parameter(description = "ID of the Ogl5 character sheet to update", required = true)
            @PathVariable Long id,
            @Parameter(description = "Ogl5 character sheet to update", required = true)
            @RequestBody CharacterSheetUpdateRequestApiDto characterSheetUpdateRequestApiDto
    ) {
        return characterSheetService.updateCharacterSheet(id, characterSheetUpdateRequestApiDto);
    }

    @Operation(summary = "Delete an Ogl5 character sheet")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ogl5 character sheet deleted"),
            @ApiResponse(responseCode = "404", description = "Ogl5 character sheet not found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Error at character sheet deletion", content = @Content)
    })
    @DeleteMapping("/{id}")
    public void deleteCharacterSheet(
            @Parameter(description = "ID of the Ogl5 character sheet to delete", required = true)
            @PathVariable Long id
    ) {
        characterSheetService.deleteCharacterSheet(id);
    }

}
