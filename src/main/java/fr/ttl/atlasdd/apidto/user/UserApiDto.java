package fr.ttl.atlasdd.apidto.user;

import fr.ttl.atlasdd.apidto.BaseApiDto;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class UserApiDto extends BaseApiDto {

    @NotNull
    @Size(max = 35, message = "Le pseudo ne peut pas dépasser 35 caractères")
    @Pattern(regexp = "^[a-zA-Z0-9_-]*$", message = "Le pseudo ne peut contenir que des lettres, des chiffres, des tirets et des underscores")
    private String pseudo;

    @NotNull
    @Size(max = 35, message = "Le slug ne peut pas dépasser 35 caractères")
    @Pattern(regexp = "^[a-z0-9_-]*$", message = "Le slug ne peut contenir que des lettres en minuscule, des chiffres, des tirets et des underscores")
    private String slug;

    @NotNull
    @Email(message = "L'adresse email doit être valide")
    private String email;

    @NotNull
    @Size(min = 8, max = 50, message = "Le mot de passe doit contenir entre 8 et 50 caractères")
    @Pattern(
            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*()]).*$",
            message = "Le mot de passe doit contenir au moins une minuscule, une majuscule, un chiffre et un caractère spécial."
    )
    private String password;

    @Override
    public String toString() {
        return "UserApiDto{" +
                "id=" + getId() +
                ", pseudo='" + pseudo + '\'' +
                ", slug='" + slug + '\'' +
                ", email='" + email + '\'' +
                ", password='[PROTECTED]'" +
                '}';
    }
}
