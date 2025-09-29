package fr.ttl.atlasdd.security;

import fr.ttl.atlasdd.repository.character.NoteCharacterRepo;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component("characterNoteSecurityService")
public class CharacterNoteSecurityService {

    private final NoteCharacterRepo noteCharacterRepository;

    public CharacterNoteSecurityService(NoteCharacterRepo noteCharacterRepository) {
        this.noteCharacterRepository = noteCharacterRepository;
    }

    /**
     * Vérifie si l'utilisateur authentifié est le propriétaire de la note,
     * en vérifiant la propriété du personnage parent (OGL5 ou Custom).
     */
    public boolean isNoteOwner(Authentication authentication, Long noteId) {
        String userEmail = authentication.getName();

        return noteCharacterRepository.findById(noteId).map(note -> {
            if (note.getOgl5CharacterSheet() != null) {
                // La note appartient à un personnage OGL5
                return note.getOgl5CharacterSheet().getOwner().getEmail().equals(userEmail);
            } else if (note.getCustomCharacterSheet() != null) {
                // La note appartient à un personnage Custom
                return note.getCustomCharacterSheet().getOwner().getEmail().equals(userEmail);
            }
            // La note n'est liée à aucun personnage, accès refusé par sécurité
            return false;
        }).orElse(false); // Si la note n'existe pas, accès refusé
    }
}
