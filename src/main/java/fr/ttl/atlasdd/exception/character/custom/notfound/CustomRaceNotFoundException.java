package fr.ttl.atlasdd.exception.character.custom.notfound;

import lombok.Getter;

@Getter
public class CustomRaceNotFoundException extends RuntimeException{

    private final int statusCode;

    public CustomRaceNotFoundException(String message) {
        super(message);
        this.statusCode = 404;
    }
}
