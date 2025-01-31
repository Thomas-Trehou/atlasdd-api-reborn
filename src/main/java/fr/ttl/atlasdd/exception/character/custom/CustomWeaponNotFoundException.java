package fr.ttl.atlasdd.exception.character.custom;

import lombok.Getter;

@Getter
public class CustomWeaponNotFoundException extends RuntimeException{

    private final int statusCode;

    public CustomWeaponNotFoundException(String message, int statusCode) {
        super(message);
        this.statusCode = 404;
    }
}
