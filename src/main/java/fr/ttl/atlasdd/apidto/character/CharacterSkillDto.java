package fr.ttl.atlasdd.apidto.character;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CharacterSkillDto {

    private Long id;
    private String name;
    private boolean expert = false;
}