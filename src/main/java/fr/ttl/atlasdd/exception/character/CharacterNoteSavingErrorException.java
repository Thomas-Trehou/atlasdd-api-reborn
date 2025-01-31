package fr.ttl.atlasdd.exception.character;

import lombok.Getter;

@Getter
public class CharacterNoteSavingErrorException extends RuntimeException{

    private final int statusCode;

    public CharacterNoteSavingErrorException(String message, int statusCode) {
        super(message);
        this.statusCode = 500;
    }
}
