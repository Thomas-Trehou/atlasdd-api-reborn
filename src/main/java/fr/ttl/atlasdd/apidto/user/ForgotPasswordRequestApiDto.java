package fr.ttl.atlasdd.apidto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ForgotPasswordRequestApiDto {

    @NotBlank(message = "L'email ne peut pas être vide.")
    @Email(message = "L'adresse email doit être valide.")
    private String email;
}
