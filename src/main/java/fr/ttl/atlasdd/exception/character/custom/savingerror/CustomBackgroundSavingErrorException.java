package fr.ttl.atlasdd.exception.character.custom.savingerror;

import lombok.Getter;

@Getter
public class CustomBackgroundSavingErrorException extends RuntimeException{

    private final int statusCode;

    public CustomBackgroundSavingErrorException(String message) {
        super(message);
        this.statusCode = 500;
    }
}
