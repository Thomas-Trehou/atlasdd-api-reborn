package fr.ttl.atlasdd.controller.custom;

import fr.ttl.atlasdd.apidto.custom.CustomCharacterSheetApiDto;
import fr.ttl.atlasdd.apidto.custom.CustomCharacterSheetCreateRequestApiDto;
import fr.ttl.atlasdd.apidto.custom.CustomCharacterSheetUpdateRequestApiDto;
import fr.ttl.atlasdd.service.custom.CustomCharacterSheetService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/custom/characters")
public class CustomCharacterSheetController {

    private CustomCharacterSheetService customCharacterSheetService;

    public CustomCharacterSheetController(CustomCharacterSheetService customCharacterSheetService) {
        this.customCharacterSheetService = customCharacterSheetService;
    }

    @PostMapping
    public CustomCharacterSheetApiDto createCustomCharacterSheet(@RequestBody CustomCharacterSheetCreateRequestApiDto customCharacterSheetCreateRequestApiDto) {
        return customCharacterSheetService.createCharacterSheet(customCharacterSheetCreateRequestApiDto);
    }

    @GetMapping("/{id}")
    public CustomCharacterSheetApiDto getCustomCharacterSheet(@PathVariable Long id) {
        return customCharacterSheetService.getCharacterSheetById(id);
    }

    @GetMapping("/users/{userId}")
    public List<CustomCharacterSheetApiDto> getCustomCharacterSheetsByUserId(@PathVariable Long userId) {
        return customCharacterSheetService.getCharacterSheetsByUserId(userId);
    }

    @PatchMapping("/{id}")
    public CustomCharacterSheetApiDto updateCustomCharacterSheet(@PathVariable Long id, @RequestBody CustomCharacterSheetUpdateRequestApiDto customCharacterSheetUpdateRequestApiDto) {
        return customCharacterSheetService.updateCharacterSheet(id, customCharacterSheetUpdateRequestApiDto);
    }
}
