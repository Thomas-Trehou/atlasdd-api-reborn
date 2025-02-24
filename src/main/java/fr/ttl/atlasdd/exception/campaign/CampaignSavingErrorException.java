package fr.ttl.atlasdd.exception.campaign;

import lombok.Getter;

@Getter
public class CampaignSavingErrorException extends RuntimeException{

    private final int statusCode;

    public CampaignSavingErrorException(String message) {
        super(message);
        this.statusCode = 500;
    }
}
