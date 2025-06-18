package fr.ttl.atlasdd.apidto.character;

import fr.ttl.atlasdd.apidto.BaseApiDto;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
    @Size(max = 75, message = "Le titre de la note doit contenir maximum 75 caractères")
    private String title;

    @NotNull
    @Size(max = 3000, message = "Le contenu de la note doit contenir maximum 3000 caractères")
    private String content;
}
