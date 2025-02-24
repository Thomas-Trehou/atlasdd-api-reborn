package fr.ttl.atlasdd.exception;

import fr.ttl.atlasdd.apidto.ErrorResponseApiDto;
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
    public ResponseEntity<ErrorResponseApiDto> handleCustomException(CustomGeneralException ex) {
        ErrorResponseApiDto errorResponseApiDto = new ErrorResponseApiDto(ex.getMessage());
        return ResponseEntity.status(ex.getStatusCode()).body(errorResponseApiDto);
    }

    // Not found exception

    @ExceptionHandler(CampaignNotFoundException.class)
    public ResponseEntity<ErrorResponseApiDto> handleCustomException(CampaignNotFoundException ex) {
        ErrorResponseApiDto errorResponseApiDto = new ErrorResponseApiDto(ex.getMessage());
        return ResponseEntity.status(ex.getStatusCode()).body(errorResponseApiDto);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponseApiDto> handleCustomException(UserNotFoundException ex) {
        ErrorResponseApiDto errorResponseApiDto = new ErrorResponseApiDto(ex.getMessage());
        return ResponseEntity.status(ex.getStatusCode()).body(errorResponseApiDto);
    }

    @ExceptionHandler(CampaignNoteNotFoundException.class)
    public ResponseEntity<ErrorResponseApiDto> handleCustomException(CampaignNoteNotFoundException ex) {
        ErrorResponseApiDto errorResponseApiDto = new ErrorResponseApiDto(ex.getMessage());
        return ResponseEntity.status(ex.getStatusCode()).body(errorResponseApiDto);
    }

    @ExceptionHandler(Ogl5CharacterNotFoundException.class)
    public ResponseEntity<ErrorResponseApiDto> handleCustomException(Ogl5CharacterNotFoundException ex) {
        ErrorResponseApiDto errorResponseApiDto = new ErrorResponseApiDto(ex.getMessage());
        return ResponseEntity.status(ex.getStatusCode()).body(errorResponseApiDto);
    }

    @ExceptionHandler(CustomCharacterNotFoundException.class)
    public ResponseEntity<ErrorResponseApiDto> handleCustomException(CustomCharacterNotFoundException ex) {
        ErrorResponseApiDto errorResponseApiDto = new ErrorResponseApiDto(ex.getMessage());
        return ResponseEntity.status(ex.getStatusCode()).body(errorResponseApiDto);
    }

    @ExceptionHandler(CustomArmorNotFoundException.class)
    public ResponseEntity<ErrorResponseApiDto> handleCustomException(CustomArmorNotFoundException ex) {
        ErrorResponseApiDto errorResponseApiDto = new ErrorResponseApiDto(ex.getMessage());
        return ResponseEntity.status(ex.getStatusCode()).body(errorResponseApiDto);
    }

    @ExceptionHandler(CustomBackgroundNotFoundException.class)
    public ResponseEntity<ErrorResponseApiDto> handleCustomException(CustomBackgroundNotFoundException ex) {
        ErrorResponseApiDto errorResponseApiDto = new ErrorResponseApiDto(ex.getMessage());
        return ResponseEntity.status(ex.getStatusCode()).body(errorResponseApiDto);
    }

    @ExceptionHandler(CustomClassNotFoundException.class)
    public ResponseEntity<ErrorResponseApiDto> handleCustomException(CustomClassNotFoundException ex) {
        ErrorResponseApiDto errorResponseApiDto = new ErrorResponseApiDto(ex.getMessage());
        return ResponseEntity.status(ex.getStatusCode()).body(errorResponseApiDto);
    }

    @ExceptionHandler(CustomRaceNotFoundException.class)
    public ResponseEntity<ErrorResponseApiDto> handleCustomException(CustomRaceNotFoundException ex) {
        ErrorResponseApiDto errorResponseApiDto = new ErrorResponseApiDto(ex.getMessage());
        return ResponseEntity.status(ex.getStatusCode()).body(errorResponseApiDto);
    }

    @ExceptionHandler(CustomWeaponNotFoundException.class)
    public ResponseEntity<ErrorResponseApiDto> handleCustomException(CustomWeaponNotFoundException ex) {
        ErrorResponseApiDto errorResponseApiDto = new ErrorResponseApiDto(ex.getMessage());
        return ResponseEntity.status(ex.getStatusCode()).body(errorResponseApiDto);
    }

    @ExceptionHandler(CharacterNoteNotFoundException.class)
    public ResponseEntity<ErrorResponseApiDto> handleCustomException(CharacterNoteNotFoundException ex) {
        ErrorResponseApiDto errorResponseApiDto = new ErrorResponseApiDto(ex.getMessage());
        return ResponseEntity.status(ex.getStatusCode()).body(errorResponseApiDto);
    }

    @ExceptionHandler(FriendsInvitationNotFoundException.class)
    public ResponseEntity<ErrorResponseApiDto> handleCustomException(FriendsInvitationNotFoundException ex) {
        ErrorResponseApiDto errorResponseApiDto = new ErrorResponseApiDto(ex.getMessage());
        return ResponseEntity.status(ex.getStatusCode()).body(errorResponseApiDto);
    }

    @ExceptionHandler(Ogl5RaceNotFoundException.class)
    public ResponseEntity<ErrorResponseApiDto> handleCustomException(Ogl5RaceNotFoundException ex) {
        ErrorResponseApiDto errorResponseApiDto = new ErrorResponseApiDto(ex.getMessage());
        return ResponseEntity.status(ex.getStatusCode()).body(errorResponseApiDto);
    }

    @ExceptionHandler(Ogl5BackgroundNotFoundException.class)
    public ResponseEntity<ErrorResponseApiDto> handleCustomException(Ogl5BackgroundNotFoundException ex) {
        ErrorResponseApiDto errorResponseApiDto = new ErrorResponseApiDto(ex.getMessage());
        return ResponseEntity.status(ex.getStatusCode()).body(errorResponseApiDto);
    }

    @ExceptionHandler(Ogl5ClassNotFoundException.class)
    public ResponseEntity<ErrorResponseApiDto> handleCustomException(Ogl5ClassNotFoundException ex) {
        ErrorResponseApiDto errorResponseApiDto = new ErrorResponseApiDto(ex.getMessage());
        return ResponseEntity.status(ex.getStatusCode()).body(errorResponseApiDto);
    }

    @ExceptionHandler(CharacterSkillNotFoundException.class)
    public ResponseEntity<ErrorResponseApiDto> handleCustomException(CharacterSkillNotFoundException ex) {
        ErrorResponseApiDto errorResponseApiDto = new ErrorResponseApiDto(ex.getMessage());
        return ResponseEntity.status(ex.getStatusCode()).body(errorResponseApiDto);
    }

    @ExceptionHandler(CharacterPreparedSpellNotFoundException.class)
    public ResponseEntity<ErrorResponseApiDto> handleCustomException(CharacterPreparedSpellNotFoundException ex) {
        ErrorResponseApiDto errorResponseApiDto = new ErrorResponseApiDto(ex.getMessage());
        return ResponseEntity.status(ex.getStatusCode()).body(errorResponseApiDto);
    }

    @ExceptionHandler(Ogl5WeaponNotFoundException.class)
    public ResponseEntity<ErrorResponseApiDto> handleCustomException(Ogl5WeaponNotFoundException ex) {
        ErrorResponseApiDto errorResponseApiDto = new ErrorResponseApiDto(ex.getMessage());
        return ResponseEntity.status(ex.getStatusCode()).body(errorResponseApiDto);
    }

    @ExceptionHandler(Ogl5ArmorNotFoundException.class)
    public ResponseEntity<ErrorResponseApiDto> handleCustomException(Ogl5ArmorNotFoundException ex) {
        ErrorResponseApiDto errorResponseApiDto = new ErrorResponseApiDto(ex.getMessage());
        return ResponseEntity.status(ex.getStatusCode()).body(errorResponseApiDto);
    }

    // Saving error exception

    @ExceptionHandler(CampaignSavingErrorException.class)
    public ResponseEntity<ErrorResponseApiDto> handleCustomException(CampaignSavingErrorException ex) {
        ErrorResponseApiDto errorResponseApiDto = new ErrorResponseApiDto(ex.getMessage());
        return ResponseEntity.status(ex.getStatusCode()).body(errorResponseApiDto);
    }

    @ExceptionHandler(UserSavingErrorException.class)
    public ResponseEntity<ErrorResponseApiDto> handleCustomException(UserSavingErrorException ex) {
        ErrorResponseApiDto errorResponseApiDto = new ErrorResponseApiDto(ex.getMessage());
        return ResponseEntity.status(ex.getStatusCode()).body(errorResponseApiDto);
    }

    @ExceptionHandler(CampaignNoteSavingErrorException.class)
    public ResponseEntity<ErrorResponseApiDto> handleCustomException(CampaignNoteSavingErrorException ex) {
        ErrorResponseApiDto errorResponseApiDto = new ErrorResponseApiDto(ex.getMessage());
        return ResponseEntity.status(ex.getStatusCode()).body(errorResponseApiDto);
    }

    @ExceptionHandler(Ogl5CharacterSavingErrorException.class)
    public ResponseEntity<ErrorResponseApiDto> handleCustomException(Ogl5CharacterSavingErrorException ex) {
        ErrorResponseApiDto errorResponseApiDto = new ErrorResponseApiDto(ex.getMessage());
        return ResponseEntity.status(ex.getStatusCode()).body(errorResponseApiDto);
    }

    @ExceptionHandler(CustomCharacterSavingErrorException.class)
    public ResponseEntity<ErrorResponseApiDto> handleCustomException(CustomCharacterSavingErrorException ex) {
        ErrorResponseApiDto errorResponseApiDto = new ErrorResponseApiDto(ex.getMessage());
        return ResponseEntity.status(ex.getStatusCode()).body(errorResponseApiDto);
    }

    @ExceptionHandler(CustomArmorSavingErrorException.class)
    public ResponseEntity<ErrorResponseApiDto> handleCustomException(CustomArmorSavingErrorException ex) {
        ErrorResponseApiDto errorResponseApiDto = new ErrorResponseApiDto(ex.getMessage());
        return ResponseEntity.status(ex.getStatusCode()).body(errorResponseApiDto);
    }

    @ExceptionHandler(CustomBackgroundSavingErrorException.class)
    public ResponseEntity<ErrorResponseApiDto> handleCustomException(CustomBackgroundSavingErrorException ex) {
        ErrorResponseApiDto errorResponseApiDto = new ErrorResponseApiDto(ex.getMessage());
        return ResponseEntity.status(ex.getStatusCode()).body(errorResponseApiDto);
    }

    @ExceptionHandler(CustomClassSavingErrorException.class)
    public ResponseEntity<ErrorResponseApiDto> handleCustomException(CustomClassSavingErrorException ex) {
        ErrorResponseApiDto errorResponseApiDto = new ErrorResponseApiDto(ex.getMessage());
        return ResponseEntity.status(ex.getStatusCode()).body(errorResponseApiDto);
    }

    @ExceptionHandler(CustomRaceSavingErrorException.class)
    public ResponseEntity<ErrorResponseApiDto> handleCustomException(CustomRaceSavingErrorException ex) {
        ErrorResponseApiDto errorResponseApiDto = new ErrorResponseApiDto(ex.getMessage());
        return ResponseEntity.status(ex.getStatusCode()).body(errorResponseApiDto);
    }

    @ExceptionHandler(CustomWeaponSavingErrorException.class)
    public ResponseEntity<ErrorResponseApiDto> handleCustomException(CustomWeaponSavingErrorException ex) {
        ErrorResponseApiDto errorResponseApiDto = new ErrorResponseApiDto(ex.getMessage());
        return ResponseEntity.status(ex.getStatusCode()).body(errorResponseApiDto);
    }

    @ExceptionHandler(CharacterNoteSavingErrorException.class)
    public ResponseEntity<ErrorResponseApiDto> handleCustomException(CharacterNoteSavingErrorException ex) {
        ErrorResponseApiDto errorResponseApiDto = new ErrorResponseApiDto(ex.getMessage());
        return ResponseEntity.status(ex.getStatusCode()).body(errorResponseApiDto);
    }

    @ExceptionHandler(FriendsInvitationSavingErrorException.class)
    public ResponseEntity<ErrorResponseApiDto> handleCustomException(FriendsInvitationSavingErrorException ex) {
        ErrorResponseApiDto errorResponseApiDto = new ErrorResponseApiDto(ex.getMessage());
        return ResponseEntity.status(ex.getStatusCode()).body(errorResponseApiDto);
    }

    // Bad request exception

    @ExceptionHandler(EmailAlreadyUsedException.class)
    public ResponseEntity<ErrorResponseApiDto> handleCustomException(EmailAlreadyUsedException ex) {
        ErrorResponseApiDto errorResponseApiDto = new ErrorResponseApiDto(ex.getMessage());
        return ResponseEntity.status(ex.getStatusCode()).body(errorResponseApiDto);
    }

    @ExceptionHandler(PseudoAlreadyUsedException.class)
    public ResponseEntity<ErrorResponseApiDto> handleCustomException(PseudoAlreadyUsedException ex) {
        ErrorResponseApiDto errorResponseApiDto = new ErrorResponseApiDto(ex.getMessage());
        return ResponseEntity.status(ex.getStatusCode()).body(errorResponseApiDto);
    }

    @ExceptionHandler(IncorrectEmailOrPasswordException.class)
    public ResponseEntity<ErrorResponseApiDto> handleCustomException(IncorrectEmailOrPasswordException ex) {
        ErrorResponseApiDto errorResponseApiDto = new ErrorResponseApiDto(ex.getMessage());
        return ResponseEntity.status(ex.getStatusCode()).body(errorResponseApiDto);
    }

    @ExceptionHandler(EmailNotVerifiedException.class)
    public ResponseEntity<ErrorResponseApiDto> handleCustomException(EmailNotVerifiedException ex) {
        ErrorResponseApiDto errorResponseApiDto = new ErrorResponseApiDto(ex.getMessage());
        return ResponseEntity.status(ex.getStatusCode()).body(errorResponseApiDto);
    }

}
