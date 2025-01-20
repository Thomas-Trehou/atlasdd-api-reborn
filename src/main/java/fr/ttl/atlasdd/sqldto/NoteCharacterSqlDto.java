package fr.ttl.atlasdd.sqldto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table(name = "CharacterNotes")
public class NoteCharacterSqlDto extends BaseSqlDto {

    private String title;

    @Column(columnDefinition = "TEXT")
    private String content;

    @ManyToMany(mappedBy = "characterNotes")
    private List<CharacterSheetSqlDto> characterSheets;
}
