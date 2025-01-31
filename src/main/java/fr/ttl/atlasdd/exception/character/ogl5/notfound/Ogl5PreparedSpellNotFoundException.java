package fr.ttl.atlasdd.exception.character.ogl5.notfound;

import lombok.Getter;

@Getter
public class Ogl5PreparedSpellNotFoundException extends RuntimeException{

    private final int statusCode;

    public Ogl5PreparedSpellNotFoundException(String message, int statusCode) {
        super(message);
        this.statusCode = 404;
    }
}
