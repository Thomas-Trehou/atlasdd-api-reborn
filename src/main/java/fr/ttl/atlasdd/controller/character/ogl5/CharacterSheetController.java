package fr.ttl.atlasdd.controller.character.ogl5;

import fr.ttl.atlasdd.apidto.character.ogl5.CharacterSheetApiDto;
import fr.ttl.atlasdd.apidto.character.ogl5.CharacterSheetCreateRequestApiDto;
import fr.ttl.atlasdd.apidto.character.ogl5.CharacterSheetUpdateRequestApiDto;
import fr.ttl.atlasdd.service.character.ogl5.CharacterSheetService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ogl5/characters")
public class CharacterSheetController {

    private CharacterSheetService characterSheetService;

    public CharacterSheetController(CharacterSheetService characterSheetService) {
        this.characterSheetService = characterSheetService;
    }

    @PostMapping
    public CharacterSheetApiDto createCharacterSheet(@RequestBody CharacterSheetCreateRequestApiDto characterSheetCreateRequestApiDto) {
        return characterSheetService.createCharacterSheet(characterSheetCreateRequestApiDto);
    }

    @GetMapping("/{id}")
    public CharacterSheetApiDto getCharacterSheet(@PathVariable Long id) {
        return characterSheetService.getCharacterSheetById(id);
    }

    @GetMapping("/users/{userId}")
    public List<CharacterSheetApiDto> getCharacterSheetsByUserId(@PathVariable Long userId) {
        return characterSheetService.getCharacterSheetsByUserId(userId);
    }

    @PatchMapping("/{id}")
    public CharacterSheetApiDto updateCharacterSheet(@PathVariable Long id, @RequestBody CharacterSheetUpdateRequestApiDto characterSheetUpdateRequestApiDto) {
        return characterSheetService.updateCharacterSheet(id, characterSheetUpdateRequestApiDto);
    }

}
