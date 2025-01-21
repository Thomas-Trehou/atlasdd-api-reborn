package fr.ttl.atlasdd.controller.ogl5;

import fr.ttl.atlasdd.apidto.ogl5.CharacterSheetApiDto;
import fr.ttl.atlasdd.apidto.ogl5.CharacterSheetRequestApiDto;
import fr.ttl.atlasdd.service.ogl5.CharacterSheetService;
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

    @PatchMapping("/{id}")
    public CharacterSheetApiDto updateCharacterSheet(@PathVariable Long id, @RequestBody CharacterSheetRequestApiDto characterSheetRequestApiDto) {
        return characterSheetService.updateCharacterSheet(id, characterSheetRequestApiDto);
    }

}
