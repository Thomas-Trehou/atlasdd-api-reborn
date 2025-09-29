package fr.ttl.atlasdd.security;

import fr.ttl.atlasdd.repository.character.ogl5.CharacterSheetRepo;
import fr.ttl.atlasdd.repository.user.UserRepo;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component("ogl5CharacterSecurityService")
public class Ogl5CharacterSecurityService {

    private final CharacterSheetRepo characterSheetRepo;
    private final UserRepo userRepository;


    public Ogl5CharacterSecurityService(CharacterSheetRepo characterSheetRepo, UserRepo userRepository) {
        this.characterSheetRepo = characterSheetRepo;
        this.userRepository = userRepository;
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

    /**
     * Vérifie si l'ID utilisateur fourni correspond à celui de l'utilisateur authentifié.
     */
    public boolean isCurrentUser(Authentication authentication, Long userIdFromRequest) {
        if (userIdFromRequest == null) {
            return false; // Ou lever une exception si l'ID ne doit jamais être null
        }

        // On récupère l'utilisateur authentifié depuis la DB pour avoir son ID
        return userRepository.findByEmail(authentication.getName())
                .map(user -> user.getId() == userIdFromRequest)
                .orElse(false);
    }
}
