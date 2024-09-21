package team.hackerping.nanuri.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import team.hackerping.nanuri.security.handler.AccessHandler;
import team.hackerping.nanuri.security.handler.AuthenticationHandler;

@Configuration
public class HandlerConfig {

    @Bean
    public AccessHandler entryPointHandler() {
        return new AccessHandler();
    }

    @Bean
    public AuthenticationHandler authenticationHandler() {
        return new AuthenticationHandler();
    }
}
