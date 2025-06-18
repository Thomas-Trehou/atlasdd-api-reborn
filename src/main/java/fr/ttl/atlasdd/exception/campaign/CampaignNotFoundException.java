package fr.ttl.atlasdd.exception.campaign;

import lombok.Getter;

@Getter
public class CampaignNotFoundException extends RuntimeException{

    private final int statusCode;

    public CampaignNotFoundException(String message) {
        super(message);
        this.statusCode = 404;
    }
}
