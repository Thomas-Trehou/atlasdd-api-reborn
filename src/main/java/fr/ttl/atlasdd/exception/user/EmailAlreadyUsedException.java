package fr.ttl.atlasdd.exception.user;

import lombok.Getter;

@Getter
public class EmailAlreadyUsedException extends RuntimeException {

    private final int statusCode;

    public EmailAlreadyUsedException(String message) {
        super(message);
        this.statusCode = 409;
    }
}
