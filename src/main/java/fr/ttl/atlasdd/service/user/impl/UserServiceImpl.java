package fr.ttl.atlasdd.service.user.impl;

import fr.ttl.atlasdd.apidto.campaign.CampaignApiDto;
import fr.ttl.atlasdd.apidto.user.SignInDto;
import fr.ttl.atlasdd.apidto.user.UserApiDto;
import fr.ttl.atlasdd.apidto.user.UserLightApiDto;
import fr.ttl.atlasdd.apidto.user.UserLightAuthApiDto;
import fr.ttl.atlasdd.entity.user.User;
import fr.ttl.atlasdd.exception.user.*;
import fr.ttl.atlasdd.mapper.user.UserLightAuthMapper;
import fr.ttl.atlasdd.mapper.user.UserLightMapper;
import fr.ttl.atlasdd.mapper.user.UserMapper;
import fr.ttl.atlasdd.repository.user.UserRepo;
import fr.ttl.atlasdd.service.campaign.CampaignNoteService;
import fr.ttl.atlasdd.service.campaign.CampaignService;
import fr.ttl.atlasdd.service.user.UserService;
import fr.ttl.atlasdd.utils.exception.ExceptionMessage;
import fr.ttl.atlasdd.utils.user.JwtTokenProvider;
import fr.ttl.atlasdd.utils.user.UserState;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
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
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepo userRepository;
    private final JavaMailSender javaMailSender;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserMapper userMapper;
    private final UserLightAuthMapper userLightAuthMapper;
    private final CampaignService campaignService;
    private final CampaignNoteService campaignNoteService;
    private final UserLightMapper userLightMapper;

    @Value("${MAIL_ADDRESS}")
    private String mailAddress;

    @Override
    public UserLightApiDto getCurrentUser(String token) {
        String userMail = jwtTokenProvider.getMailFromToken(token.replace("Bearer ", ""));
        return userRepository.findByEmail(userMail)
                .map(userLightMapper::toApiDto)
                .orElseThrow(() -> new UserNotFoundException(ExceptionMessage.USER_NOT_FOUND.getMessage()));
    }

    @Override
    public UserLightApiDto getUserById(Long id) {
        return userRepository.findById(id)
                .map(userLightMapper::toApiDto)
                .orElseThrow(() -> new UserNotFoundException(ExceptionMessage.USER_NOT_FOUND.getMessage()));
    }

    @Override
    public UserLightApiDto getUserBySlug(String slug) {
        return userRepository.findBySlug(slug)
                .map(userLightMapper::toApiDto)
                .orElse(null);
    }

    @Override
    public List<UserLightApiDto> getFriends(Long userId) {

        Optional<User> userToFind = userRepository.findById(userId);

        if (userToFind.isEmpty()) {
            throw new UserNotFoundException(ExceptionMessage.USER_NOT_FOUND.getMessage());
        }

        return userToFind.get().getFriends().stream()
                .map(userLightMapper::toApiDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserLightApiDto createUser(UserApiDto userApiDto) {
        Optional<User> existingUser = userRepository.findByEmail(userApiDto.getEmail());

        if (existingUser.isPresent()) {
            throw new EmailAlreadyUsedException(ExceptionMessage.USER_EMAIL_ALREADY_USED.getMessage());
        }

        userApiDto.setPassword(bCryptPasswordEncoder.encode(userApiDto.getPassword()));

        User user = userMapper.toEntity(userApiDto);
        user.setSlug(userApiDto.getPseudo().toLowerCase().replace(" ", "-"));

        User userWithSameSlug = userRepository.findBySlug(user.getSlug()).orElse(null);

        if (userWithSameSlug != null) {
            throw new PseudoAlreadyUsedException(ExceptionMessage.USER_PSEUDO_ALREADY_USED.getMessage());
        }

        user.setState(UserState.INACTIVE);

        try {
            userRepository.save(user);
        } catch (Exception e) {
            throw new UserSavingErrorException(ExceptionMessage.USER_SAVE_ERROR.getMessage());
        }

        String token = UUID.randomUUID().toString();
        storeTokenInSession(token, user);
        sendVerificationEmail(user, token);

        return userLightMapper.toApiDto(user);
    }

    private void storeTokenInSession(String token, User user) {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session = attr.getRequest().getSession(true);
        session.setAttribute("verificationToken", token);
        session.setAttribute("user", user);
    }

    private void sendVerificationEmail(User user, String token) {
        String url = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/utilisateurs/verify")
                .queryParam("token", token)
                .toUriString();

        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(user.getEmail());
        email.setFrom(mailAddress);
        email.setSubject("Vérification de l'adresse email");
        email.setText("Cliquez sur le lien pour vérifier votre adresse email : " + url);
        javaMailSender.send(email);
    }

    @Override
    public String verifyToken(String token, HttpSession session) {
        String sessionToken = (String) session.getAttribute("verificationToken");
        User user = (User) session.getAttribute("user");

        if (sessionToken == null || !sessionToken.equals(token)) {
            return "Token invalide";
        }

        user.setState(UserState.ACTIVE);

        try {
            userRepository.save(user);
        } catch (Exception e) {
            throw new UserSavingErrorException(ExceptionMessage.USER_UPDATE_ERROR.getMessage());
        }

        return "Compte vérifié avec succès";
    }

    @Override
    public UserLightAuthApiDto signIn(SignInDto signInDto) {
        Optional<User> user = userRepository.findByEmail(signInDto.getEmail());

        if (user.isEmpty() || !bCryptPasswordEncoder.matches(signInDto.getPassword(), user.get().getPassword())) {
            throw new IncorrectEmailOrPasswordException(ExceptionMessage.USER_EMAIL_OR_PASSWORD_INVALID.getMessage());
        }

        if (user.get().getState() != UserState.ACTIVE) {
            throw new EmailNotVerifiedException(ExceptionMessage.USER_EMAIL_NOT_VERIFIED.getMessage());
        }

        UserLightAuthApiDto userLightAuthApiDto = userLightAuthMapper.toApiDto(user.get());

        userLightAuthApiDto.setToken(jwtTokenProvider.generateToken(userLightAuthApiDto));

        return userLightAuthApiDto;
    }

    @Override
    public void deleteUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(ExceptionMessage.USER_NOT_FOUND.getMessage()));

        List<CampaignApiDto> campaigns = campaignService.getCampaignsAsPlayer(id);
        campaignService.deletePlayerFromCampaigns(campaigns, id);
        campaignService.deleteCampaignsAsDungeonMaster(id);

        campaignNoteService.deleteCampaignNotesByUserId(id);

        for (User friend : user.getFriends()) {
            friend.getFriends().remove(user);
            userRepository.save(friend);
        }

        user.getFriends().clear();
        userRepository.save(user);

        try {
            userRepository.delete(user);
        } catch (Exception e) {
            throw new UserSavingErrorException(ExceptionMessage.USER_DELETE_ERROR.getMessage());
        }
    }

}
