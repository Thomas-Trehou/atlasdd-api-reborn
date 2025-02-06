package fr.ttl.atlasdd.exception.character.ogl5.notfound;

import lombok.Getter;

@Getter
public class Ogl5ArmorNotFoundException extends RuntimeException{

    private final int statusCode;

    public Ogl5ArmorNotFoundException(String message) {
        super(message);
        this.statusCode = 404;
    }
}
