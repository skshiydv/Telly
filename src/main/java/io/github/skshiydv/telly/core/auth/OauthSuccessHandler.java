package io.github.skshiydv.telly.core.auth;

import io.github.skshiydv.telly.user.entity.UserEntity;
import io.github.skshiydv.telly.user.repository.UserRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.catalina.authenticator.jaspic.PersistentProviderRegistrations;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
@Component
public class OauthSuccessHandler implements AuthenticationSuccessHandler {
    private final UserRepository userRepository;

    public OauthSuccessHandler(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        response.sendRedirect("/dashboard");
        OAuth2User user = (OAuth2User) authentication.getPrincipal();
        UserEntity userEntity = new UserEntity();
        userEntity.setEmail(user.getAttribute("email"));
        userEntity.setUsername(user.getAttribute("name"));
        userEntity.setImageUrl(user.getAttribute("picture"));
        UserEntity user2=userRepository.findByEmail(user.getAttribute("email"));
        if(user2==null){
            userRepository.save(userEntity);
        }
    }
}
