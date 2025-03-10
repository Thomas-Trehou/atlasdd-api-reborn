package fr.ttl.atlasdd.controller.campaign;

import fr.ttl.atlasdd.apidto.campaign.CampaignNoteApiDto;
import fr.ttl.atlasdd.service.campaign.CampaignNoteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/campaign-notes")
@RequiredArgsConstructor
public class CampaignNoteController {

    private final CampaignNoteService campaignNoteService;

    @Operation(summary = "Create a campaign note")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200", description = "Campaign note created, returns the created campaign"),
            @ApiResponse(responseCode = "404", description = "Campaign not found / " + "User not found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Error at campaign note saving", content = @Content)
    })
    @PostMapping("/campaigns/{campaignId}/users/{userId}")
    public CampaignNoteApiDto createCampaignNote(
            @Parameter(description = "ID of the campaign", required = true)
            @PathVariable Long campaignId,
            @Parameter(description = "ID of the user", required = true)
            @PathVariable Long userId,
            @Parameter(description = "Campaign note to create", required = true)
            @RequestBody CampaignNoteApiDto campaignNoteApiDto
    ) {
        return campaignNoteService.createCampaignNote(campaignId, userId, campaignNoteApiDto);
    }

    @Operation(summary = "Get a campaign note by ID")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200", description = "Campaign note found, returns the campaign note"),
            @ApiResponse(responseCode = "404", description = "Campaign note not found", content = @Content)
    })
    @GetMapping("/{id}")
    public CampaignNoteApiDto getCampaignNoteById(
            @Parameter(description = "ID of the campaign note", required = true)
            @PathVariable Long id
    ) {
        return campaignNoteService.getCampaignNoteById(id);
    }

    @Operation(summary = "Get all campaign notes by campaign ID and user ID")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200", description = "Campaign notes found, returns the campaign notes"),
            @ApiResponse(responseCode = "500", description = "Something goes wrong at list creation", content = @Content)
    })
    @GetMapping("/campaigns/{campaignId}/users/{userId}")
    public List<CampaignNoteApiDto> getCampaignNoteByCampaignIdAndUserId(
            @Parameter(description = "ID of the campaign", required = true)
            @PathVariable Long campaignId,
            @Parameter(description = "ID of the user", required = true)
            @PathVariable Long userId
    ) {
        return campaignNoteService.getCampaignNotesByCampaignIdAndUserId(campaignId, userId);
    }

    @Operation(summary = "Update a campaign note")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200", description = "Campaign note updated, returns the updated campaign note"),
            @ApiResponse(responseCode = "404", description = "Campaign note not found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Error at campaign note saving", content = @Content)
    })
    @PatchMapping("/{id}")
    public CampaignNoteApiDto updateCampaignNote(
            @Parameter(description = "ID of the campaign note to update", required = true)
            @PathVariable Long id,
            @Parameter(description = "Campaign note to update", required = true)
            @RequestBody CampaignNoteApiDto campaignNoteApiDto
    ) {
        return campaignNoteService.updateCampaignNoteById(id, campaignNoteApiDto);
    }

    @Operation(summary = "Delete a campaign note")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200", description = "Campaign note deleted"),
            @ApiResponse(responseCode = "500", description = "Error at campaign not deletion", content = @Content)
    })
    @DeleteMapping("/{id}")
    public void deleteCampaignNoteById(
            @Parameter(description = "ID of the campaign note to delete", required = true)
            @PathVariable Long id
    ) {
        campaignNoteService.deleteCampaignNoteById(id);
    }
}
