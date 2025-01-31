package fr.ttl.atlasdd.exception.character.custom.notfound;

import lombok.Getter;

@Getter
public class CustomCharacterNotFoundException extends RuntimeException{

    private final int statusCode;

    public CustomCharacterNotFoundException(String message, int statusCode) {
        super(message);
        this.statusCode = 404;
    }
}
