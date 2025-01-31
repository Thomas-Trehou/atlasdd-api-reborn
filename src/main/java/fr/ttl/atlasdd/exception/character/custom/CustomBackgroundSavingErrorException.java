package fr.ttl.atlasdd.exception.character.custom;

import lombok.Getter;

@Getter
public class CustomBackgroundSavingErrorException extends RuntimeException{

    private final int statusCode;

    public CustomBackgroundSavingErrorException(String message, int statusCode) {
        super(message);
        this.statusCode = 500;
    }
}
