package fr.ttl.atlasdd.controller;

import fr.ttl.atlasdd.apidto.CharacterSheetApiDto;
import fr.ttl.atlasdd.apidto.CharacterSheetRequestApiDto;
import fr.ttl.atlasdd.service.CharacterSheetService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
