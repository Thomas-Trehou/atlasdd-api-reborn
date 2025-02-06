package fr.ttl.atlasdd.exception.user;

import lombok.Getter;

@Getter
public class PseudoAlreadyUsedException extends RuntimeException {

    private final int statusCode;

    public PseudoAlreadyUsedException(String message) {
        super(message);
        this.statusCode = 400;
    }
}
