package team.hackerping.nanuri.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import team.hackerping.nanuri.security.application.JwtProvider;
import team.hackerping.nanuri.security.filter.JwtAuthorizationFilter;

@Configuration
public class FilterConfig {

    @Bean
    public JwtAuthorizationFilter jwtAuthorizationFilter(JwtProvider jwtProvider) {
        return new JwtAuthorizationFilter(jwtProvider);
    }

    @Bean
    public CorsFilter corsFilter() {
        var source = new UrlBasedCorsConfigurationSource();
        var config = new CorsConfiguration();

        config.setAllowCredentials(true);
        config.addAllowedOrigin("*");
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        source.registerCorsConfiguration("/**", config);

        return new CorsFilter(source);
    }
}
