package fr.ttl.atlasdd.apidto.user;

import lombok.Data;

@Data
public class SignInDto {

    private String email;
    private String password;
}
