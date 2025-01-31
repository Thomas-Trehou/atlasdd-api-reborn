package fr.ttl.atlasdd.exception;

import fr.ttl.atlasdd.exception.campaign.CampaignNotFoundException;
import fr.ttl.atlasdd.exception.campaign.CampaignSavingErrorException;
import fr.ttl.atlasdd.exception.user.UserNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomGeneralException.class)
    public ResponseEntity<String> handleCustomException(CustomGeneralException ex) {
        return ResponseEntity.status(ex.getStatusCode()).body(ex.getMessage());
    }

    // Not found exception

    @ExceptionHandler(CampaignNotFoundException.class)
    public ResponseEntity<String> handleCustomException(CampaignNotFoundException ex) {
        return ResponseEntity.status(ex.getStatusCode()).body(ex.getMessage());
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<String> handleCustomException(UserNotFoundException ex) {
        return ResponseEntity.status(ex.getStatusCode()).body(ex.getMessage());
    }

    // Saving error exception

    @ExceptionHandler(CampaignSavingErrorException.class)
    public ResponseEntity<String> handleCustomException(CampaignSavingErrorException ex) {
        return ResponseEntity.status(ex.getStatusCode()).body(ex.getMessage());
    }



}
