package fr.ttl.atlasdd.apidto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class SignInDto {

    @NotNull
    @Email(message = "L'adresse email doit être valide")
    private String email;

    @NotNull
    @Size(min = 8, max = 50, message = "Le mot de passe doit contenir entre 8 et 50 caractères"
    )
    private String password;
}
