package fr.ttl.atlasdd.exception.user;

import lombok.Getter;

@Getter
public class FriendsInvitationSavingErrorException extends RuntimeException{

    private final int statusCode;

    public FriendsInvitationSavingErrorException(String message) {
        super(message);
        this.statusCode = 500;
    }
}
