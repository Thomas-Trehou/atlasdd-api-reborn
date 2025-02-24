package fr.ttl.atlasdd.exception.character;

import lombok.Getter;

@Getter
public class CharacterSkillNotFoundException extends RuntimeException{

    private final int statusCode;

    public CharacterSkillNotFoundException(String message) {
        super(message);
        this.statusCode = 404;
    }
}
