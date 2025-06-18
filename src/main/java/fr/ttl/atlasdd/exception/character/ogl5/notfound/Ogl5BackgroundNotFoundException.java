package fr.ttl.atlasdd.exception.character.ogl5.notfound;

import lombok.Getter;

@Getter
public class Ogl5BackgroundNotFoundException extends RuntimeException{

    private final int statusCode;

    public Ogl5BackgroundNotFoundException(String message) {
        super(message);
        this.statusCode = 404;
    }
}
