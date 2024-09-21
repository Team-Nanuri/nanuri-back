package team.hackerping.nanuri.security.application;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "jwt")
public record JwtProperties(
        String accessTokenSecretKey,
        String refreshTokenSecretKey,
        Long accessTokenExpireIn,
        Long refreshTokenExpireIn
) {
}
