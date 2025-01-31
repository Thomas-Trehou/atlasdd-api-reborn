package fr.ttl.atlasdd.exception.character;

import lombok.Getter;

@Getter
public class CharacterNoteNotFoundException extends RuntimeException{

    private final int statusCode;

    public CharacterNoteNotFoundException(String message, int statusCode) {
        super(message);
        this.statusCode = 404;
    }
}
