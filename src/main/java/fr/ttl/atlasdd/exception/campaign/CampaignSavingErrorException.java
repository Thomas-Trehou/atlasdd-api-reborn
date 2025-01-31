package fr.ttl.atlasdd.exception.campaign;

import lombok.Getter;

@Getter
public class CampaignSavingErrorException extends RuntimeException{

    private final int statusCode;

    public CampaignSavingErrorException(String message, int statusCode) {
        super(message);
        this.statusCode = statusCode;
    }
}
