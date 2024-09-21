package team.hackerping.nanuri.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import team.hackerping.nanuri.security.application.JwtProperties;
import team.hackerping.nanuri.security.application.JwtProvider;
import team.hackerping.nanuri.security.application.JwtService;
import team.hackerping.nanuri.security.application.PrincipleDetailService;
import team.hackerping.nanuri.user.persistence.UserRepository;

@Configuration
public class SecurityServiceConfig {

    @Bean
    public UserDetailsService userDetailsService(
            UserRepository userRepository
    ) {
        return new PrincipleDetailService(userRepository);
    }

    @Bean
    public JwtProvider jwtProvider(
            JwtProperties jwtProperties
    ) {
        return new JwtProvider(jwtProperties);
    }

    @Bean
    public JwtService jwtService(
            JwtProvider jwtProvider
    ) {
        return new JwtService(jwtProvider);
    }
}
