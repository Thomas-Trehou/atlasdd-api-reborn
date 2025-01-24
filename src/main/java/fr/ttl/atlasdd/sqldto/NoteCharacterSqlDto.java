package fr.ttl.atlasdd.sqldto;

import fr.ttl.atlasdd.sqldto.custom.CustomCharacterSheetSqlDto;
import fr.ttl.atlasdd.sqldto.ogl5.CharacterSheetSqlDto;
import jakarta.persistence.*;
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

    @ManyToOne
    @JoinColumn(name = "ogl5CharacterSheet_id")
    private CharacterSheetSqlDto ogl5CharacterSheet;

    @ManyToOne
    @JoinColumn(name = "customCharacterSheet_id")
    private CustomCharacterSheetSqlDto customCharacterSheet;
}
