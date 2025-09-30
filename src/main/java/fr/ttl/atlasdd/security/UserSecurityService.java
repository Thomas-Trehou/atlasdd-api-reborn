package fr.ttl.atlasdd.security;

import fr.ttl.atlasdd.repository.user.UserRepo;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component("userSecurityService")
public class UserSecurityService {

    private final UserRepo userRepository;

    public UserSecurityService(UserRepo userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Vérifie si l'ID utilisateur fourni correspond à celui de l'utilisateur authentifié.
     * C'est la méthode principale pour s'assurer qu'un utilisateur n'agit que sur son propre compte.
     * @param authentication L'objet d'authentification fourni par Spring Security.
     * @param userIdFromRequest L'ID de l'utilisateur ciblé par la requête.
     * @return true si l'utilisateur est le propriétaire du compte, false sinon.
     */
    public boolean isCurrentUser(Authentication authentication, Long userIdFromRequest) {
        if (userIdFromRequest == null) {
            return false;
        }

        // On récupère l'utilisateur authentifié depuis la DB pour avoir son ID
        return userRepository.findByEmail(authentication.getName())
                .map(user -> user.getId() == userIdFromRequest)
                .orElse(false);
    }
}