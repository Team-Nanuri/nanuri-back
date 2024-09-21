package team.hackerping.nanuri.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
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
}
