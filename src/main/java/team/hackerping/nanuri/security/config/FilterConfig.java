package team.hackerping.nanuri.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import team.hackerping.nanuri.security.application.JwtProvider;
import team.hackerping.nanuri.security.filter.JwtAuthorizationFilter;

@Configuration
public class FilterConfig {

    @Bean
    public JwtAuthorizationFilter jwtAuthorizationFilter(JwtProvider jwtProvider) {
        return new JwtAuthorizationFilter(jwtProvider);
    }
}
