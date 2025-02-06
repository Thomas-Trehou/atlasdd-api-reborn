package fr.ttl.atlasdd.exception.character.custom.notfound;

import lombok.Getter;

@Getter
public class CustomWeaponNotFoundException extends RuntimeException{

    private final int statusCode;

    public CustomWeaponNotFoundException(String message) {
        super(message);
        this.statusCode = 404;
    }
}
