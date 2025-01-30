package fr.ttl.atlasdd.service.user.impl;

import fr.ttl.atlasdd.apidto.user.SignInDto;
import fr.ttl.atlasdd.apidto.user.UserApiDto;
import fr.ttl.atlasdd.apidto.user.UserLightApiDto;
import fr.ttl.atlasdd.apidto.user.UserLightAuthApiDto;
import fr.ttl.atlasdd.mapper.user.UserLightAuthMapper;
import fr.ttl.atlasdd.mapper.user.UserLightMapper;
import fr.ttl.atlasdd.mapper.user.UserMapper;
import fr.ttl.atlasdd.repository.user.UserRepo;
import fr.ttl.atlasdd.service.user.UserService;
import fr.ttl.atlasdd.sqldto.user.UserSqlDto;
import fr.ttl.atlasdd.utils.user.JwtTokenProvider;
import fr.ttl.atlasdd.utils.user.UserState;
import jakarta.servlet.http.HttpSession;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepo userRepository;
    private final JavaMailSender javaMailSender;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserMapper userMapper;
    private final UserLightAuthMapper userLightAuthMapper;

    public UserServiceImpl(
            UserRepo userRepository,
            JavaMailSender javaMailSender,
            JwtTokenProvider jwtTokenProvider,
            UserMapper userMapper,
            UserLightAuthMapper userLightAuthMapper) {
        this.userRepository = userRepository;
        this.javaMailSender = javaMailSender;
        this.bCryptPasswordEncoder = new BCryptPasswordEncoder();
        this.jwtTokenProvider = jwtTokenProvider;
        this.userMapper = userMapper;
        this.userLightAuthMapper = userLightAuthMapper;
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

    @Override
    public List<UserLightApiDto> getFriends(Long userId) {
        return userRepository.findById(userId)
                .map(user -> user.getFriends().stream()
                        .map(UserLightMapper.INSTANCE::toApiDto)
                        .collect(Collectors.toList()))
                .orElse(null);
    }

    @Override
    public UserLightApiDto createUser(UserApiDto userApiDto) {
        Optional<UserSqlDto> existingUser = userRepository.findByEmail(userApiDto.getEmail());

        if (existingUser.isPresent()) {
            return null;
        }

        userApiDto.setPassword(bCryptPasswordEncoder.encode(userApiDto.getPassword()));

        UserSqlDto userSqlDto = userMapper.toSqlDto(userApiDto);
        userSqlDto.setSlug(userApiDto.getPseudo().toLowerCase().replace(" ", "-"));
        userSqlDto.setState(UserState.INACTIVE);
        userRepository.save(userSqlDto);

        String token = UUID.randomUUID().toString();
        storeTokenInSession(token, userSqlDto);
        sendVerificationEmail(userSqlDto, token);

        return UserLightMapper.INSTANCE.toApiDto(userSqlDto);
    }

    private void storeTokenInSession(String token, UserSqlDto user) {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session = attr.getRequest().getSession(true);
        session.setAttribute("verificationToken", token);
        session.setAttribute("user", user);
    }

    private void sendVerificationEmail(UserSqlDto user, String token) {
        String url = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/utilisateurs/verify")
                .queryParam("token", token)
                .toUriString();

        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(user.getEmail());
        email.setFrom("atlasroyaumesoublies@gmail.com");
        email.setSubject("Vérification de l'adresse email");
        email.setText("Cliquez sur le lien pour vérifier votre adresse email : " + url);
        javaMailSender.send(email);
    }

    @Override
    public String verifyToken(String token, HttpSession session) {
        String sessionToken = (String) session.getAttribute("verificationToken");
        UserSqlDto user = (UserSqlDto) session.getAttribute("user");

        if (sessionToken == null || !sessionToken.equals(token)) {
            System.out.println("Token de session: " + sessionToken);
            System.out.println("Token fourni: " + token);
            return "Token invalide";
        }

        user.setState(UserState.ACTIVE);
        userRepository.save(user);

        return "Compte vérifié avec succès";
    }

    @Override
    public UserLightAuthApiDto signIn(SignInDto signInDto) {
        Optional<UserSqlDto> user = userRepository.findByEmail(signInDto.getEmail());

        if (user.isEmpty() || !bCryptPasswordEncoder.matches(signInDto.getPassword(), user.get().getPassword())) {
            return null;
        }

        if (user.get().getState() != UserState.ACTIVE) {
            return null;
        }

        UserLightAuthApiDto userLightAuthApiDto = userLightAuthMapper.toApiDto(user.get());

        userLightAuthApiDto.setToken(jwtTokenProvider.generateToken(userLightAuthApiDto));

        return userLightAuthApiDto;
    }

}
