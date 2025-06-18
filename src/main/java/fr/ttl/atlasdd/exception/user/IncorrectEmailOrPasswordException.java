package fr.ttl.atlasdd.exception.user;

import lombok.Getter;

@Getter
public class IncorrectEmailOrPasswordException extends RuntimeException {

    private final int statusCode;

    public IncorrectEmailOrPasswordException(String message) {
        super(message);
        this.statusCode = 400;
    }
}
