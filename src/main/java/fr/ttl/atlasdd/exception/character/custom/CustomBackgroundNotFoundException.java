package fr.ttl.atlasdd.exception.character.custom;

import lombok.Getter;

@Getter
public class CustomBackgroundNotFoundException extends RuntimeException{

    private final int statusCode;

    public CustomBackgroundNotFoundException(String message, int statusCode) {
        super(message);
        this.statusCode = 404;
    }
}
