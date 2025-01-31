package fr.ttl.atlasdd.exception.campaign;

import lombok.Getter;

@Getter
public class CampaignNoteSavingErrorException extends RuntimeException{

    private final int statusCode;

    public CampaignNoteSavingErrorException(String message, int statusCode) {
        super(message);
        this.statusCode = statusCode;
    }
}
