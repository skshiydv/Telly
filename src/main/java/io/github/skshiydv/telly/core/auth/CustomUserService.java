package io.github.skshiydv.telly.core.auth;

import io.github.skshiydv.telly.user.entity.UserEntity;
import io.github.skshiydv.telly.user.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Objects;
@Slf4j
@Service
public class CustomUserService extends DefaultOAuth2UserService {

    private final UserRepository userRepository;

    public CustomUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

        log.info("Loading user");
        OAuth2User oauthUser = super.loadUser(userRequest);

        String email = oauthUser.getAttribute("email");
        String name = oauthUser.getAttribute("name");
        String picture = oauthUser.getAttribute("picture");
        String providerId = oauthUser.getAttribute("sub"); // for Google
        String provider = userRequest.getClientRegistration().getRegistrationId(); // "google"

        UserEntity existingUser = userRepository.findByEmail(email);
        UserEntity user;
        user = Objects.requireNonNullElseGet(existingUser, UserEntity::new);

        user.setEmail(email);
        user.setUsername(name);
        user.setImageUrl(picture);
        user.setProvider(provider);
        user.setProviderId(providerId);

        userRepository.save(user);

        return oauthUser;
    }
}
