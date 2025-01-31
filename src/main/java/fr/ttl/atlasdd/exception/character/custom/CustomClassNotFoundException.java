package fr.ttl.atlasdd.exception.character.custom;

import lombok.Getter;

@Getter
public class CustomClassNotFoundException extends RuntimeException{

    private final int statusCode;

    public CustomClassNotFoundException(String message, int statusCode) {
        super(message);
        this.statusCode = 404;
    }
}
