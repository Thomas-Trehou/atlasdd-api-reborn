package fr.ttl.atlasdd.exception.character;

import lombok.Getter;

@Getter
public class OptionNotFoundException extends RuntimeException {

    private final int statusCode;

    public OptionNotFoundException(String message) {
        super(message);
        this.statusCode = 404;
    }
}
