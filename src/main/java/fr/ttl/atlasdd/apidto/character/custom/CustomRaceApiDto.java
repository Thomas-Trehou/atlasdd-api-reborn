package fr.ttl.atlasdd.apidto.character.custom;

import fr.ttl.atlasdd.apidto.BaseApiDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class CustomRaceApiDto extends BaseApiDto {

    private String name;
    private String speed;
    private String languages;
    private String traits;
}
