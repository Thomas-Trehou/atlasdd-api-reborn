package fr.ttl.atlasdd.exception.user;

import lombok.Getter;

@Getter
public class UserNotFoundException extends RuntimeException{

    private final int statusCode;

    public UserNotFoundException(String message, int statusCode) {
        super(message);
        this.statusCode = 404;
    }
}
