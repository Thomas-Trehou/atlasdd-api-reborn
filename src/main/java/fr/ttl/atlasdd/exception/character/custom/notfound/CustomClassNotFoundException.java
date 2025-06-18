package fr.ttl.atlasdd.exception.character.custom.notfound;

import lombok.Getter;

@Getter
public class CustomClassNotFoundException extends RuntimeException{

    private final int statusCode;

    public CustomClassNotFoundException(String message) {
        super(message);
        this.statusCode = 404;
    }
}
