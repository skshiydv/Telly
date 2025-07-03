package io.github.skshiydv.telly.core.config;

import io.github.skshiydv.telly.core.auth.CustomUserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final CustomUserService customOAuth2UserService;

    public SecurityConfig(CustomUserService customOAuth2UserService) {
        this.customOAuth2UserService = customOAuth2UserService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorizeRequests -> authorizeRequests
                        .requestMatchers("/auth/**").permitAll()
                        .anyRequest().authenticated())
                .oauth2Login(
                        oauth -> oauth.defaultSuccessUrl("/dashboard/", true)
                                .userInfoEndpoint(userInfo -> userInfo
                                        .userService(customOAuth2UserService))
                ).logout(logout -> logout.logoutUrl("/auth/logout"));

        return http.build();
    }
}
