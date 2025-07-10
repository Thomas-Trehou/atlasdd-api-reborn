package fr.ttl.atlasdd.controller.campaign;

import fr.ttl.atlasdd.apidto.campaign.CampaignApiDto;
import fr.ttl.atlasdd.apidto.campaign.CampaignCreateRequestApiDto;
import fr.ttl.atlasdd.service.campaign.CampaignService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/campaigns")
@RequiredArgsConstructor
public class CampaignController {

    private final CampaignService campaignService;

    @Operation(summary = "Create a campaign")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200", description = "Campaign created, returns the created campaign"),
            @ApiResponse(responseCode = "404", description = "User not found", content = {}),
            @ApiResponse(responseCode = "500", description = "Error at campaign saving", content = {})
    })
    @PostMapping
    public CampaignApiDto createCampaign(
            @Parameter(description = "Campaign to create", required = true)
            @RequestBody CampaignCreateRequestApiDto campaignCreateRequestApiDto
    ) {
        return campaignService.createCampaign(campaignCreateRequestApiDto);
    }

    @Operation(summary = "Get a campaign by ID")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200", description = "Campaign found, returns the campaign"),
            @ApiResponse(responseCode = "404", description = "Campaign not found", content = {})
    })
    @GetMapping("/{id}")
    public CampaignApiDto getCampaign(
            @Parameter(description = "ID of the campaign", required = true)
            @PathVariable Long id
    ) {
        return campaignService.getCampaignById(id);
    }

    @Operation(summary = "Get all campaign by userId")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200", description = "Campaigns found, returns the campaigns"),
            @ApiResponse(responseCode = "404", description = "Error when trying to retrieve campaigns", content = {})
    })
    @GetMapping("/users/{userId}")
    public List<CampaignApiDto> getCampaignsByUserId(
            @Parameter(description = "ID of the user", required = true)
            @PathVariable Long userId
    ) {
        return campaignService.getAllByUserId(userId);
    }

    @Operation(summary = "Update a campaign")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200", description = "Campaign updated, returns the updated campaign"),
            @ApiResponse(responseCode = "404", description = "User not found / " + "Campaign not found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Error at campaign creation", content = @Content)
    })
    @PatchMapping("/{id}")
    public CampaignApiDto updateCampaign(
            @Parameter(description = "ID of the campaign to update", required = true)
            @PathVariable Long id,
            @Parameter(description = "Campaign to update", required = true)
            @RequestBody CampaignCreateRequestApiDto campaignCreateRequestApiDto
    ) {
        return campaignService.updateCampaign(id, campaignCreateRequestApiDto);
    }

    @Operation(summary = "Add a player to a campaign")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200", description = "Campaign updated, returns the updated campaign"),
            @ApiResponse(responseCode = "404", description = "User not found / " + "Campaign not found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Error at campaign saving", content = @Content)
    })
    @PatchMapping("/{id}/add-player/{playerId}")
    public CampaignApiDto addPlayerToCampaign(
            @Parameter(description = "ID of the campaign", required = true)
            @PathVariable Long id,
            @Parameter(description = "ID of the player to add", required = true)
            @PathVariable Long playerId
    ) {
        return campaignService.addPlayerToCampaign(id, playerId);
    }

    @Operation(summary = "Remove a player from a campaign")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200", description = "Campaign updated, returns the updated campaign"),
            @ApiResponse(responseCode = "404", description = "User not found / " + "Campaign not found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Error at campaign saving", content = @Content)
    })
    @DeleteMapping("/{id}/remove-player/{playerId}")
    public CampaignApiDto removePlayerFromCampaign(
            @Parameter(description = "ID of the campaign", required = true)
            @PathVariable Long id,
            @Parameter(description = "ID of the player to remove", required = true)
            @PathVariable Long playerId
    ) {
        return campaignService.removePlayerFromCampaign(id, playerId);
    }

    @Operation(summary = "Add an Ogl5 character to a campaign")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200", description = "Campaign updated, returns the updated campaign"),
            @ApiResponse(responseCode = "404", description = "Personnage non trouvé / " + "Campaign not found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Error at campaign saving", content = @Content)
    })
    @PostMapping("/{id}/add-ogl5-character/{characterId}")
    public CampaignApiDto addOgl5CharacterToCampaign(
            @Parameter(description = "ID of the campaign", required = true)
            @PathVariable Long id,
            @Parameter(description = "ID of the Ogl5 character to add", required = true)
            @PathVariable Long characterId
    ) {
        return campaignService.addOgl5CharacterToCampaign(id, characterId);
    }

    @Operation(summary = "Remove a Ogl5 from a campaign")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200", description = "Campaign updated, returns the updated campaign"),
            @ApiResponse(responseCode = "404", description = "Character non trouvé / " + "Campaign not found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Error at campaign saving", content = @Content)
    })
    @DeleteMapping("/{id}/remove-ogl5-character/{characterId}")
    public CampaignApiDto removeOgl5CharacterFromCampaign(
            @Parameter(description = "ID of the campaign", required = true)
            @PathVariable Long id,
            @Parameter(description = "ID of the Ogl5 character to remove", required = true)
            @PathVariable Long characterId
    ) {
        return campaignService.removeOgl5CharacterFromCampaign(id, characterId);
    }

    @Operation(summary = "Add an Custom character to a campaign")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200", description = "Campaign updated, returns the updated campaign"),
            @ApiResponse(responseCode = "404", description = "Personnage non trouvé / " + "Campaign not found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Error at campaign saving", content = @Content)
    })
    @PostMapping("/{id}/add-custom-character/{characterId}")
    public CampaignApiDto addCustomCharacterToCampaign(
            @Parameter(description = "ID of the campaign", required = true)
            @PathVariable Long id,
            @Parameter(description = "ID of the Custom character to add", required = true)
            @PathVariable Long characterId
    ) {
        return campaignService.addCustomCharacterToCampaign(id, characterId);
    }

    @Operation(summary = "Remove a Ogl5 from a campaign")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200", description = "Campaign updated, returns the updated campaign"),
            @ApiResponse(responseCode = "404", description = "Character non trouvé / " + "Campaign not found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Error at campaign saving", content = @Content)
    })
    @DeleteMapping("/{id}/remove-custom-character/{characterId}")
    public CampaignApiDto removeCustomCharacterFromCampaign(
            @Parameter(description = "ID of the campaign", required = true)
            @PathVariable Long id,
            @Parameter(description = "ID of the Custom character to remove", required = true)
            @PathVariable Long characterId
    ) {
        return campaignService.removeCustomCharacterFromCampaign(id, characterId);
    }

    @Operation(summary = "Delete a campaign")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "500", description = "Error at campaign saving", content = @Content)
    })
    @DeleteMapping("/{id}")
    public void deleteCampaign(
            @Parameter(description = "ID of the campaign to delete", required = true)
            @PathVariable Long id
    ) {
        campaignService.deleteCampaign(id);
    }

}
