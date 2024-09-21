package team.hackerping.nanuri.security.application;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;
import java.util.Map;
import java.util.UUID;
import java.util.function.Function;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class JwtProvider {
    private final JwtProperties jwtProperties;

    public String generateAccessToken(Map<String, Object> claims) {
        return Jwts.builder()
                .setClaims(claims)
                .setId(UUID.randomUUID().toString())
                .setIssuedAt(now())
                .setExpiration(expirationWith(JwtProperties::accessTokenExpireIn))
                .signWith(SignatureAlgorithm.HS512, jwtProperties.accessTokenSecretKey())
                .compact();
    }

    public String generateRefreshToken(Map<String, Object> claims) {
        return Jwts.builder()
                .setClaims(claims)
                .setId(UUID.randomUUID().toString())
                .setIssuedAt(now())
                .setExpiration(expirationWith(JwtProperties::refreshTokenExpireIn))
                .signWith(SignatureAlgorithm.HS512, jwtProperties.refreshTokenSecretKey())
                .compact();
    }

    private Date now() {
        return new Date(System.currentTimeMillis());
    }

    private Date expirationWith(Function<JwtProperties, Long> expiration) {
        return new Date(System.currentTimeMillis() + expiration.apply(jwtProperties));
    }
}
