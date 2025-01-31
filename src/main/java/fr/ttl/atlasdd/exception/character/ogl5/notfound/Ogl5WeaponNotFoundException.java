package fr.ttl.atlasdd.exception.character.ogl5.notfound;

import lombok.Getter;

@Getter
public class Ogl5WeaponNotFoundException extends RuntimeException{

    private final int statusCode;

    public Ogl5WeaponNotFoundException(String message, int statusCode) {
        super(message);
        this.statusCode = 404;
    }
}
