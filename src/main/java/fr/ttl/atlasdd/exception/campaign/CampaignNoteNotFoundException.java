package fr.ttl.atlasdd.exception.campaign;

import lombok.Getter;

@Getter
public class CampaignNoteNotFoundException extends RuntimeException{

    private final int statusCode;

    public CampaignNoteNotFoundException(String message, int statusCode) {
        super(message);
        this.statusCode = 404;
    }
}
