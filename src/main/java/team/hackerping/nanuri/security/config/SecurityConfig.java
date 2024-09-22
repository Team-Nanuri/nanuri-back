package team.hackerping.nanuri.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer.FrameOptionsConfig;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.intercept.AuthorizationFilter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.CorsFilter;
import team.hackerping.nanuri.security.filter.JwtAuthorizationFilter;
import team.hackerping.nanuri.security.handler.AccessHandler;
import team.hackerping.nanuri.security.handler.AuthenticationHandler;

@Configuration
@EnableWebSecurity
@EnableGlobalAuthentication
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity httpSecurity,
            JwtAuthorizationFilter jwtAuthorizationFilter,
            SecurityProperties securityProperties,
            AccessHandler accessHandler,
            AuthenticationHandler authenticationHandler,
            CorsFilter corsFilter
    ) throws Exception {
        httpSecurity.csrf(AbstractHttpConfigurer::disable);
        httpSecurity.httpBasic(AbstractHttpConfigurer::disable);
        httpSecurity.sessionManagement(sessionManagement -> sessionManagement
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        );
        
        httpSecurity.headers(headers -> headers
                .frameOptions(FrameOptionsConfig::disable)
        );

        httpSecurity.authorizeHttpRequests(authorizeRequests -> authorizeRequests
                .requestMatchers(securityProperties.loginProcessingUrl()).permitAll()
                .requestMatchers(securityProperties.authBaseUrl()).permitAll()
                .requestMatchers(securityProperties.swaggerUrl()).permitAll()
                .requestMatchers(securityProperties.permitAllUrls()).permitAll()
                .anyRequest().authenticated()
        );

        httpSecurity.exceptionHandling(exceptionHandling -> exceptionHandling
                .authenticationEntryPoint(accessHandler)
                .accessDeniedHandler(accessHandler)
        );

        httpSecurity.formLogin(formLogin -> formLogin
                .loginProcessingUrl(securityProperties.loginProcessingUrl())
                .successHandler(authenticationHandler)
                .failureHandler(authenticationHandler)
        );

        httpSecurity.addFilterBefore(corsFilter, UsernamePasswordAuthenticationFilter.class);
        httpSecurity.addFilterBefore(jwtAuthorizationFilter, AuthorizationFilter.class);
        return httpSecurity.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}