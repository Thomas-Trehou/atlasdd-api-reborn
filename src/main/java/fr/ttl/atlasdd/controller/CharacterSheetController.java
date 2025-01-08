package fr.ttl.atlasdd.controller;

import fr.ttl.atlasdd.apidto.CharacterSheetApiDto;
import fr.ttl.atlasdd.apidto.CharacterSheetRequestApiDto;
import fr.ttl.atlasdd.service.CharacterSheetService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/characters")
public class CharacterSheetController {

    private CharacterSheetService characterSheetService;

    public CharacterSheetController(CharacterSheetService characterSheetService) {
        this.characterSheetService = characterSheetService;
    }

    @PostMapping
    public CharacterSheetApiDto createCharacterSheet(@RequestBody CharacterSheetRequestApiDto characterSheetRequestApiDto) {
        return characterSheetService.createCharacterSheet(characterSheetRequestApiDto);
    }

    @GetMapping("/{id}")
    public CharacterSheetApiDto getCharacterSheet(@PathVariable Long id) {
        return characterSheetService.getCharacterSheetById(id);
    }

    @GetMapping("/users/{userId}")
    public List<CharacterSheetApiDto> getCharacterSheetsByUserId(@PathVariable Long userId) {
        return characterSheetService.getCharacterSheetsByUserId(userId);
    }

}
