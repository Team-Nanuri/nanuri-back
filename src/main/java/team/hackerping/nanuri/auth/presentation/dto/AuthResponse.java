package team.hackerping.nanuri.auth.presentation.dto;

public class AuthResponse {

    public record TokenResponse(
            String accessToken,
            String refreshToken
    ) { }
}
