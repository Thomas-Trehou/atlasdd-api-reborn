package fr.ttl.atlasdd.exception.character.custom.savingerror;

import lombok.Getter;

@Getter
public class CustomRaceSavingErrorException extends RuntimeException {

    private final int statusCode;

    public CustomRaceSavingErrorException(String message, int statusCode) {
        super(message);
        this.statusCode = 500;
    }
}
