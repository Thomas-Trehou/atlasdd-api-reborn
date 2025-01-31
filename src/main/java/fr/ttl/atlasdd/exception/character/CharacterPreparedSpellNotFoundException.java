package fr.ttl.atlasdd.exception.character;

import lombok.Getter;

@Getter
public class CharacterPreparedSpellNotFoundException extends RuntimeException{

    private final int statusCode;

    public CharacterPreparedSpellNotFoundException(String message, int statusCode) {
        super(message);
        this.statusCode = 404;
    }
}
