package fr.ttl.atlasdd.exception.character.custom;

import lombok.Getter;

@Getter
public class CustomWeaponSavingErrorException extends RuntimeException{

    private final int statusCode;

    public CustomWeaponSavingErrorException(String message, int statusCode) {
        super(message);
        this.statusCode = 500;
    }
}
