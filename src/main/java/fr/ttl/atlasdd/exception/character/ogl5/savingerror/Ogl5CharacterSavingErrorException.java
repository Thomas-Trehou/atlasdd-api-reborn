package fr.ttl.atlasdd.exception.character.ogl5.savingerror;

import lombok.Getter;

@Getter
public class Ogl5CharacterSavingErrorException extends RuntimeException{

    private final int statusCode;

    public Ogl5CharacterSavingErrorException(String message, int statusCode) {
        super(message);
        this.statusCode = statusCode;
    }
}
