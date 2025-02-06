package fr.ttl.atlasdd.exception;

import fr.ttl.atlasdd.exception.campaign.CampaignNotFoundException;
import fr.ttl.atlasdd.exception.campaign.CampaignNoteNotFoundException;
import fr.ttl.atlasdd.exception.campaign.CampaignNoteSavingErrorException;
import fr.ttl.atlasdd.exception.campaign.CampaignSavingErrorException;
import fr.ttl.atlasdd.exception.character.CharacterNoteNotFoundException;
import fr.ttl.atlasdd.exception.character.CharacterNoteSavingErrorException;
import fr.ttl.atlasdd.exception.character.CharacterPreparedSpellNotFoundException;
import fr.ttl.atlasdd.exception.character.CharacterSkillNotFoundException;
import fr.ttl.atlasdd.exception.character.custom.notfound.*;
import fr.ttl.atlasdd.exception.character.custom.savingerror.*;
import fr.ttl.atlasdd.exception.character.ogl5.notfound.*;
import fr.ttl.atlasdd.exception.character.ogl5.savingerror.Ogl5CharacterSavingErrorException;
import fr.ttl.atlasdd.exception.user.*;
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

    @ExceptionHandler(CustomArmorNotFoundException.class)
    public ResponseEntity<String> handleCustomException(CustomArmorNotFoundException ex) {
        return ResponseEntity.status(ex.getStatusCode()).body(ex.getMessage());
    }

    @ExceptionHandler(CustomBackgroundNotFoundException.class)
    public ResponseEntity<String> handleCustomException(CustomBackgroundNotFoundException ex) {
        return ResponseEntity.status(ex.getStatusCode()).body(ex.getMessage());
    }

    @ExceptionHandler(CustomClassNotFoundException.class)
    public ResponseEntity<String> handleCustomException(CustomClassNotFoundException ex) {
        return ResponseEntity.status(ex.getStatusCode()).body(ex.getMessage());
    }

    @ExceptionHandler(CustomRaceNotFoundException.class)
    public ResponseEntity<String> handleCustomException(CustomRaceNotFoundException ex) {
        return ResponseEntity.status(ex.getStatusCode()).body(ex.getMessage());
    }

    @ExceptionHandler(CustomWeaponNotFoundException.class)
    public ResponseEntity<String> handleCustomException(CustomWeaponNotFoundException ex) {
        return ResponseEntity.status(ex.getStatusCode()).body(ex.getMessage());
    }

    @ExceptionHandler(CharacterNoteNotFoundException.class)
    public ResponseEntity<String> handleCustomException(CharacterNoteNotFoundException ex) {
        return ResponseEntity.status(ex.getStatusCode()).body(ex.getMessage());
    }

    @ExceptionHandler(FriendsInvitationNotFoundException.class)
    public ResponseEntity<String> handleCustomException(FriendsInvitationNotFoundException ex) {
        return ResponseEntity.status(ex.getStatusCode()).body(ex.getMessage());
    }

    @ExceptionHandler(Ogl5RaceNotFoundException.class)
    public ResponseEntity<String> handleCustomException(Ogl5RaceNotFoundException ex) {
        return ResponseEntity.status(ex.getStatusCode()).body(ex.getMessage());
    }

    @ExceptionHandler(Ogl5BackgroundNotFoundException.class)
    public ResponseEntity<String> handleCustomException(Ogl5BackgroundNotFoundException ex) {
        return ResponseEntity.status(ex.getStatusCode()).body(ex.getMessage());
    }

    @ExceptionHandler(Ogl5ClassNotFoundException.class)
    public ResponseEntity<String> handleCustomException(Ogl5ClassNotFoundException ex) {
        return ResponseEntity.status(ex.getStatusCode()).body(ex.getMessage());
    }

    @ExceptionHandler(CharacterSkillNotFoundException.class)
    public ResponseEntity<String> handleCustomException(CharacterSkillNotFoundException ex) {
        return ResponseEntity.status(ex.getStatusCode()).body(ex.getMessage());
    }

    @ExceptionHandler(CharacterPreparedSpellNotFoundException.class)
    public ResponseEntity<String> handleCustomException(CharacterPreparedSpellNotFoundException ex) {
        return ResponseEntity.status(ex.getStatusCode()).body(ex.getMessage());
    }

    @ExceptionHandler(Ogl5WeaponNotFoundException.class)
    public ResponseEntity<String> handleCustomException(Ogl5WeaponNotFoundException ex) {
        return ResponseEntity.status(ex.getStatusCode()).body(ex.getMessage());
    }

    @ExceptionHandler(Ogl5ArmorNotFoundException.class)
    public ResponseEntity<String> handleCustomException(Ogl5ArmorNotFoundException ex) {
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

    @ExceptionHandler(CustomArmorSavingErrorException.class)
    public ResponseEntity<String> handleCustomException(CustomArmorSavingErrorException ex) {
        return ResponseEntity.status(ex.getStatusCode()).body(ex.getMessage());
    }

    @ExceptionHandler(CustomBackgroundSavingErrorException.class)
    public ResponseEntity<String> handleCustomException(CustomBackgroundSavingErrorException ex) {
        return ResponseEntity.status(ex.getStatusCode()).body(ex.getMessage());
    }

    @ExceptionHandler(CustomClassSavingErrorException.class)
    public ResponseEntity<String> handleCustomException(CustomClassSavingErrorException ex) {
        return ResponseEntity.status(ex.getStatusCode()).body(ex.getMessage());
    }

    @ExceptionHandler(CustomRaceSavingErrorException.class)
    public ResponseEntity<String> handleCustomException(CustomRaceSavingErrorException ex) {
        return ResponseEntity.status(ex.getStatusCode()).body(ex.getMessage());
    }

    @ExceptionHandler(CustomWeaponSavingErrorException.class)
    public ResponseEntity<String> handleCustomException(CustomWeaponSavingErrorException ex) {
        return ResponseEntity.status(ex.getStatusCode()).body(ex.getMessage());
    }

    @ExceptionHandler(CharacterNoteSavingErrorException.class)
    public ResponseEntity<String> handleCustomException(CharacterNoteSavingErrorException ex) {
        return ResponseEntity.status(ex.getStatusCode()).body(ex.getMessage());
    }

    @ExceptionHandler(FriendsInvitationSavingErrorException.class)
    public ResponseEntity<String> handleCustomException(FriendsInvitationSavingErrorException ex) {
        return ResponseEntity.status(ex.getStatusCode()).body(ex.getMessage());
    }

    // Bad request exception

    @ExceptionHandler(EmailAlreadyUsedException.class)
    public ResponseEntity<String> handleCustomException(EmailAlreadyUsedException ex) {
        return ResponseEntity.status(ex.getStatusCode()).body(ex.getMessage());
    }

    @ExceptionHandler(PseudoAlreadyUsedException.class)
    public ResponseEntity<String> handleCustomException(PseudoAlreadyUsedException ex) {
        return ResponseEntity.status(ex.getStatusCode()).body(ex.getMessage());
    }

    @ExceptionHandler(IncorrectEmailOrPasswordException.class)
    public ResponseEntity<String> handleCustomException(IncorrectEmailOrPasswordException ex) {
        return ResponseEntity.status(ex.getStatusCode()).body(ex.getMessage());
    }

    @ExceptionHandler(EmailNotVerifiedException.class)
    public ResponseEntity<String> handleCustomException(EmailNotVerifiedException ex) {
        return ResponseEntity.status(ex.getStatusCode()).body(ex.getMessage());
    }

}
