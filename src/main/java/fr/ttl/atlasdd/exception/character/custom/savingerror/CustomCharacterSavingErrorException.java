package fr.ttl.atlasdd.exception.character.custom.savingerror;

import lombok.Getter;

@Getter
public class CustomCharacterSavingErrorException extends RuntimeException{

    private final int statusCode;

    public CustomCharacterSavingErrorException(String message) {
        super(message);
        this.statusCode = 500;
    }
}
