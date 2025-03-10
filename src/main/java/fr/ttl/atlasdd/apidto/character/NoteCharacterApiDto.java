package fr.ttl.atlasdd.apidto.character;

import fr.ttl.atlasdd.apidto.BaseApiDto;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class NoteCharacterApiDto extends BaseApiDto {

    @NotNull
    private String title;

    @NotNull
    private String content;
}
