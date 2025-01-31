package fr.ttl.atlasdd.service.user;

import fr.ttl.atlasdd.mapper.user.UserDetailsMapper;
import fr.ttl.atlasdd.repository.user.UserRepo;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepo userRepository;
    private final UserDetailsMapper userDetailsMapper;

    public CustomUserDetailsService(UserRepo userRepository, UserDetailsMapper userDetailsMapper) {
        this.userRepository = userRepository;
        this.userDetailsMapper = userDetailsMapper;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email)
                .map(userDetailsMapper::toUserDetails)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));
    }
}