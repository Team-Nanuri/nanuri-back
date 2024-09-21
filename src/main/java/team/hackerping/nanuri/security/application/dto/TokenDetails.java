package team.hackerping.nanuri.security.application.dto;

public record TokenDetails(
        String accessToken,
        String refreshToken
) {
}
