package fr.ttl.atlasdd.exception.character.custom;

import lombok.Getter;

@Getter
public class CustomCharacterSavingErrorException extends RuntimeException{

    private final int statusCode;

    public CustomCharacterSavingErrorException(String message, int statusCode) {
        super(message);
        this.statusCode = 500;
    }
}
