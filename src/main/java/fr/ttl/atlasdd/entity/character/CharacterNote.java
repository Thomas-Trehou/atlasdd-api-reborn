package fr.ttl.atlasdd.entity.character;

import fr.ttl.atlasdd.entity.BaseEntity;
import fr.ttl.atlasdd.entity.character.custom.CustomCharacterSheet;
import fr.ttl.atlasdd.entity.character.ogl5.Ogl5CharacterSheet;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table(name = "CharacterNotes")
public class CharacterNote extends BaseEntity {

    private String title;

    @Column(columnDefinition = "TEXT")
    private String content;

    @ManyToOne
    @JoinColumn(name = "ogl5CharacterSheet_id")
    private Ogl5CharacterSheet ogl5CharacterSheet;

    @ManyToOne
    @JoinColumn(name = "customCharacterSheet_id")
    private CustomCharacterSheet customCharacterSheet;
}
