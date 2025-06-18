package fr.ttl.atlasdd.exception.character.custom.notfound;

import lombok.Getter;

@Getter
public class CustomBackgroundNotFoundException extends RuntimeException{

    private final int statusCode;

    public CustomBackgroundNotFoundException(String message) {
        super(message);
        this.statusCode = 404;
    }
}
