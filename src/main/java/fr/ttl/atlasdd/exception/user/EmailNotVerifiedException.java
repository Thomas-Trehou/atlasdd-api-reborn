package fr.ttl.atlasdd.exception.user;

import lombok.Getter;

@Getter
public class EmailNotVerifiedException extends RuntimeException {

    private final int statusCode;

    public EmailNotVerifiedException(String message) {
        super(message);
        this.statusCode = 400;
    }
}
