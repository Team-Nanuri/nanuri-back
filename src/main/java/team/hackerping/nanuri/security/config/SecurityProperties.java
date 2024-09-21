package team.hackerping.nanuri.security.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "security.login")
public record SecurityProperties(
        String loginProcessingUrl,
        String[] authBaseUrl
) {
}