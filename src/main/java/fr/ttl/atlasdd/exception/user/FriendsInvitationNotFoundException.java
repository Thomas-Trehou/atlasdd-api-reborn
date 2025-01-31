package fr.ttl.atlasdd.exception.user;

import lombok.Getter;

@Getter
public class FriendsInvitationNotFoundException extends RuntimeException{

    private final int statusCode;

    public FriendsInvitationNotFoundException(String message, int statusCode) {
        super(message);
        this.statusCode = 404;
    }
}
