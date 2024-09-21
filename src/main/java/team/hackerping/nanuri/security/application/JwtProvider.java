package team.hackerping.nanuri.security.application;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;
import java.util.Map;
import java.util.Objects;
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
                .setSubject((String) claims.get("username"))
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
                .setSubject((String) claims.get("username"))
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

    public boolean isInvalidAccessToken(String token) {
        return Objects.isNull(getSubjectFromToken(token, JwtProperties::accessTokenSecretKey))
                || isTokenExpired(token, JwtProperties::accessTokenSecretKey);
    }

    public boolean isInvalidRefreshToken(String token) {
        return Objects.isNull(getSubjectFromToken(token, JwtProperties::refreshTokenSecretKey))
                || isTokenExpired(token, JwtProperties::refreshTokenSecretKey);
    }

    public String getSubjectFromToken(String token, Function<JwtProperties, String> secretResolver) {
        return getClaim(token, Claims::getSubject, secretResolver);
    }

    private boolean isTokenExpired(String token, Function<JwtProperties, String> secretResolver) {
        return getClaim(token, Claims::getExpiration, secretResolver)
                .before(now());
    }

    private <T> T getClaim(String token,
                           Function<Claims, T> claimsResolver,
                           Function<JwtProperties, String> secretResolver
    ) {
        final Claims claims = Jwts.parser()
                .setSigningKey(secretResolver.apply(jwtProperties))
                .parseClaimsJws(token)
                .getBody();
        return claimsResolver.apply(claims);
    }

    public Map<String, Object> getAccessClaims(String token) {
        return getClaim(token, claims -> claims, JwtProperties::accessTokenSecretKey);
    }

    public Map<String, Object> getRefreshClaims(String refreshToken) {
        return getClaim(refreshToken, claims -> claims, JwtProperties::refreshTokenSecretKey);
    }
}
