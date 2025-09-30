package fr.ttl.atlasdd.apidto.user;

import fr.ttl.atlasdd.apidto.BaseApiDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class UserLightAuthApiDto extends BaseApiDto {

    private String pseudo;
    private String slug;
    private String email;
    private String token;

    @Override
    public String toString() {
        return "UserLightAuthApiDto{" +
                "id=" + getId() +
                ", pseudo='" + pseudo + '\'' +
                ", slug='" + slug + '\'' +
                ", email='" + email + '\'' +
                ", token='[PROTECTED]'" +
                '}';
    }
}
