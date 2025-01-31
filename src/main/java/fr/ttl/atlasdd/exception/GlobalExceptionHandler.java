package fr.ttl.atlasdd.exception;

import fr.ttl.atlasdd.exception.campaign.CampaignNotFoundException;
import fr.ttl.atlasdd.exception.campaign.CampaignNoteNotFoundException;
import fr.ttl.atlasdd.exception.campaign.CampaignNoteSavingErrorException;
import fr.ttl.atlasdd.exception.campaign.CampaignSavingErrorException;
import fr.ttl.atlasdd.exception.character.custom.CustomCharacterNotFoundException;
import fr.ttl.atlasdd.exception.character.custom.CustomCharacterSavingErrorException;
import fr.ttl.atlasdd.exception.character.ogl5.Ogl5CharacterNotFoundException;
import fr.ttl.atlasdd.exception.character.ogl5.Ogl5CharacterSavingErrorException;
import fr.ttl.atlasdd.exception.user.UserNotFoundException;
import fr.ttl.atlasdd.exception.user.UserSavingErrorException;
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

    @ExceptionHandler(CampaignNoteNotFoundException.class)
    public ResponseEntity<String> handleCustomException(CampaignNoteNotFoundException ex) {
        return ResponseEntity.status(ex.getStatusCode()).body(ex.getMessage());
    }

    @ExceptionHandler(Ogl5CharacterNotFoundException.class)
    public ResponseEntity<String> handleCustomException(Ogl5CharacterNotFoundException ex) {
        return ResponseEntity.status(ex.getStatusCode()).body(ex.getMessage());
    }

    @ExceptionHandler(CustomCharacterNotFoundException.class)
    public ResponseEntity<String> handleCustomException(CustomCharacterNotFoundException ex) {
        return ResponseEntity.status(ex.getStatusCode()).body(ex.getMessage());
    }

    // Saving error exception

    @ExceptionHandler(CampaignSavingErrorException.class)
    public ResponseEntity<String> handleCustomException(CampaignSavingErrorException ex) {
        return ResponseEntity.status(ex.getStatusCode()).body(ex.getMessage());
    }

    @ExceptionHandler(UserSavingErrorException.class)
    public ResponseEntity<String> handleCustomException(UserSavingErrorException ex) {
        return ResponseEntity.status(ex.getStatusCode()).body(ex.getMessage());
    }

    @ExceptionHandler(CampaignNoteSavingErrorException.class)
    public ResponseEntity<String> handleCustomException(CampaignNoteSavingErrorException ex) {
        return ResponseEntity.status(ex.getStatusCode()).body(ex.getMessage());
    }

    @ExceptionHandler(Ogl5CharacterSavingErrorException.class)
    public ResponseEntity<String> handleCustomException(Ogl5CharacterSavingErrorException ex) {
        return ResponseEntity.status(ex.getStatusCode()).body(ex.getMessage());
    }

    @ExceptionHandler(CustomCharacterSavingErrorException.class)
    public ResponseEntity<String> handleCustomException(CustomCharacterSavingErrorException ex) {
        return ResponseEntity.status(ex.getStatusCode()).body(ex.getMessage());
    }



}
