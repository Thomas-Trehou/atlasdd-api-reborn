package fr.ttl.atlasdd.exception.character.custom.savingerror;

import lombok.Getter;

@Getter
public class CustomArmorSavingErrorException extends RuntimeException{

    private final int statusCode;

    public CustomArmorSavingErrorException(String message) {
        super(message);
        this.statusCode = 500;
    }
}
