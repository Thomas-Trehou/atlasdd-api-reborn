package fr.ttl.atlasdd.exception.character.custom.notfound;

import lombok.Getter;

@Getter
public class CustomArmorNotFoundException extends RuntimeException{

    private final int statusCode;

    public CustomArmorNotFoundException(String message, int statusCode) {
        super(message);
        this.statusCode = 404;
    }
}
