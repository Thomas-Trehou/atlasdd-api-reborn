package fr.ttl.atlasdd.exception.character.custom;

import lombok.Getter;

@Getter
public class CustomArmorSavingErrorException extends RuntimeException{

    private final int statusCode;

    public CustomArmorSavingErrorException(String message, int statusCode) {
        super(message);
        this.statusCode = 500;
    }
}
