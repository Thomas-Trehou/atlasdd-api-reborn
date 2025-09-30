package fr.ttl.atlasdd.apidto.user;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProfileUpdateApiDto {

    @Size(max = 35, message = "Le pseudo ne peut pas dépasser 35 caractères")
    @Pattern(regexp = "^[a-zA-Z0-9_-]*$", message = "Le pseudo ne peut contenir que des lettres, des chiffres, des tirets et des underscores")
    private String pseudo;

    @Size(min = 8, max = 50, message = "Le mot de passe doit contenir entre 8 et 50 caractères")
    @Pattern(
            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*()]).*$",
            message = "Le mot de passe doit contenir au moins une minuscule, une majuscule, un chiffre et un caractère spécial."
    )
    private String password;

    @Override
    public String toString() {
        return "ProfileUpdateApiDto{" +
                "pseudo='" + pseudo + '\'' +
                ", password='[PROTECTED]'" +
                '}';
    }
}
