package fr.ttl.atlasdd.exception.character.custom;

import lombok.Getter;

@Getter
public class CustomClassSavingErrorException extends RuntimeException{

    private final int statusCode;

    public CustomClassSavingErrorException(String message, int statusCode) {
        super(message);
        this.statusCode = 500;
    }
}
