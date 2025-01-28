package fr.ttl.atlasdd.apidto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class UserLightApiDto extends BaseApiDto {

    private String pseudo;
    private String slug;
    private String email;
}
