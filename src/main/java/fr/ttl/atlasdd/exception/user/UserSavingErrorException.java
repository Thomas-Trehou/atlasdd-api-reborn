package fr.ttl.atlasdd.exception.user;

import lombok.Getter;

@Getter
public class UserSavingErrorException extends RuntimeException{

    private final int statusCode;

    public UserSavingErrorException(String message) {
        super(message);
        this.statusCode = 500;
    }
}
