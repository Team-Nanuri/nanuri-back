package team.hackerping.nanuri.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer.FrameOptionsConfig;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import team.hackerping.nanuri.security.handler.AccessHandler;
import team.hackerping.nanuri.security.handler.AuthenticationHandler;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity httpSecurity,
            SecurityProperties securityProperties,
            AccessHandler accessHandler,
            AuthenticationHandler authenticationHandler
    ) throws Exception {
        httpSecurity.csrf(AbstractHttpConfigurer::disable);
        httpSecurity.httpBasic(AbstractHttpConfigurer::disable);
        httpSecurity.headers(headers -> headers
                .frameOptions(FrameOptionsConfig::disable)
        );

        httpSecurity.exceptionHandling(exceptionHandling -> exceptionHandling
                .authenticationEntryPoint(accessHandler)
                .accessDeniedHandler(accessHandler)
        );

        // 개발의 편의성을 위해 모든 요청을 허용합니다.
        httpSecurity.formLogin(formLogin -> formLogin
                .loginProcessingUrl(securityProperties.loginProcessingUrl())
                .successHandler(authenticationHandler)
                .failureHandler(authenticationHandler)
                .permitAll()
        );

        httpSecurity.authorizeHttpRequests(authorizeRequests -> authorizeRequests
                .anyRequest().permitAll()
        );
        return httpSecurity.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}