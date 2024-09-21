package team.hackerping.nanuri.security.application;

import java.util.Map;
import lombok.RequiredArgsConstructor;
import team.hackerping.nanuri.security.application.dto.TokenDetails;

@RequiredArgsConstructor
public class JwtService {
    private final JwtProvider jwtProvider;

    public TokenDetails generateLoginToken(Map<String, Object> claims) {
        var accessToken = jwtProvider.generateAccessToken(claims);
        var refreshToken = jwtProvider.generateRefreshToken(claims);

        return new TokenDetails(accessToken, refreshToken);
    }
}
