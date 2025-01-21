package fr.ttl.atlasdd.service.global.impl;

import fr.ttl.atlasdd.apidto.UserLightApiDto;
import fr.ttl.atlasdd.mapper.UserLightMapper;
import fr.ttl.atlasdd.repository.UserRepo;
import fr.ttl.atlasdd.service.global.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepo userRepository;

    public UserServiceImpl(UserRepo userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserLightApiDto getUserById(Long id) {
        return userRepository.findById(id)
                .map(UserLightMapper.INSTANCE::toApiDto)
                .orElse(null);
    }

    @Override
    public UserLightApiDto getUserBySlug(String slug) {
        return userRepository.findBySlug(slug)
                .map(UserLightMapper.INSTANCE::toApiDto)
                .orElse(null);
    }
}
