package fr.ttl.atlasdd.security;

import fr.ttl.atlasdd.repository.character.ogl5.CharacterSheetRepo;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component("ogl5CharacterSecurityService")
public class Ogl5CharacterSecurityService {

    private final CharacterSheetRepo characterSheetRepo;

    public Ogl5CharacterSecurityService(CharacterSheetRepo characterSheetRepo) {
        this.characterSheetRepo = characterSheetRepo;
    }

    /**
     * Vérifie si l'utilisateur authentifié est le propriétaire de la fiche de personnage.
     * @param authentication L'objet d'authentification fourni par Spring Security.
     * @param characterSheetId L'ID de la fiche à vérifier.
     * @return true si l'utilisateur est le propriétaire, false sinon.
     */
    public boolean isOwner(Authentication authentication, Long characterSheetId) {
        String userEmail = authentication.getName();

        return characterSheetRepo.findById(characterSheetId)
                .map(characterSheet -> characterSheet.getOwner().getEmail().equals(userEmail))
                .orElse(false);
    }
}
