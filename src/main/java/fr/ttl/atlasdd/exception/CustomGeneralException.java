package fr.ttl.atlasdd.exception;

import lombok.Getter;

@Getter
public class CustomGeneralException extends RuntimeException{

    private final int statusCode;

    public CustomGeneralException(String message, int statusCode) {
        super(message);
        this.statusCode = statusCode;
    }

}
