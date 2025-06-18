package fr.ttl.atlasdd.controller.character.custom;

import fr.ttl.atlasdd.apidto.character.custom.CustomCharacterSheetApiDto;
import fr.ttl.atlasdd.apidto.character.custom.CustomCharacterSheetCreateRequestApiDto;
import fr.ttl.atlasdd.apidto.character.custom.CustomCharacterSheetUpdateRequestApiDto;
import fr.ttl.atlasdd.service.character.custom.CustomCharacterSheetService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/custom/characters")
@RequiredArgsConstructor
public class CustomCharacterSheetController {

    private final CustomCharacterSheetService customCharacterSheetService;

    @Operation(summary = "Create a custom character sheet")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Custom character sheet created, returns the created character sheet"),
            @ApiResponse(responseCode = "404", description =
                    "Error when trying to retrieve skills / " +
                    "Error when trying to retrieve spells / " +
                    "User not found", content = @Content),
            @ApiResponse(responseCode = "500", description =
                    "Error at race saving / " +
                    "Error at background saving / " +
                    "Error at class saving / " +
                    "Error at weapons saving / " +
                    "Error at armor saving / " +
                    "Error at character saving", content = @Content)
    })
    @PostMapping
    public CustomCharacterSheetApiDto createCustomCharacterSheet(
            @Parameter(description = "Custom character sheet to create", required = true)
            @RequestBody CustomCharacterSheetCreateRequestApiDto customCharacterSheetCreateRequestApiDto
    ) {
        return customCharacterSheetService.createCharacterSheet(customCharacterSheetCreateRequestApiDto);
    }

    @Operation(summary = "Get a custom character sheet by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Custom character sheet found, returns the character sheet"),
            @ApiResponse(responseCode = "404", description = "Custom character sheet not found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Error at character sheet retrieval", content = @Content)
    })
    @GetMapping("/{id}")
    public CustomCharacterSheetApiDto getCustomCharacterSheet(
            @Parameter(description = "ID of the custom character sheet", required = true)
            @PathVariable Long id
    ) {
        return customCharacterSheetService.getCharacterSheetById(id);
    }

    @Operation(summary = "Get all custom character sheets by user ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Custom character sheets found, returns the character sheets"),
            @ApiResponse(responseCode = "500", description = "Something goes wrong at list creation", content = @Content)
    })
    @GetMapping("/users/{userId}")
    public List<CustomCharacterSheetApiDto> getCustomCharacterSheetsByUserId(
            @Parameter(description = "ID of the user", required = true)
            @PathVariable Long userId
    ) {
        return customCharacterSheetService.getCharacterSheetsByUserId(userId);
    }

    @Operation(summary = "Update a custom character sheet")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Custom character sheet updated, returns the updated character sheet"),
            @ApiResponse(responseCode = "404", description =
                    "Race not found / " +
                    "Background not found / " +
                    "Class not found / " +
                    "Weapons not found / " +
                    "Armor not found / " +
                    "Error when trying to retrieve skills / " +
                    "Error when trying to retrieve spells / " +
                    "Custom character not found", content = @Content),
            @ApiResponse(responseCode = "500", description =
                    "Error at race saving / " +
                    "Error at background saving / " +
                    "Error at class saving / " +
                    "Error at weapons saving / " +
                    "Error at armor saving / " +
                    "Error at character saving", content = @Content)
    })
    @PatchMapping("/{id}")
    public CustomCharacterSheetApiDto updateCustomCharacterSheet(
            @Parameter(description = "ID of the custom character sheet to update", required = true)
            @PathVariable Long id,
            @Parameter(description = "Custom character sheet to update", required = true)
            @RequestBody CustomCharacterSheetUpdateRequestApiDto customCharacterSheetUpdateRequestApiDto
    ) {
        return customCharacterSheetService.updateCharacterSheet(id, customCharacterSheetUpdateRequestApiDto);
    }

    @Operation(summary = "Delete a custom character sheet")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Custom character sheet deleted"),
            @ApiResponse(responseCode = "404", description = "Custom character sheet not found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Error at character deletion", content = @Content)
    })
    @DeleteMapping("/{id}")
    public void deleteCustomCharacterSheet(
            @Parameter(description = "ID of the custom character sheet to delete", required = true)
            @PathVariable Long id
    ) {
        customCharacterSheetService.deleteCharacterSheet(id);
    }
}
