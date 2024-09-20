package team.hackerping.nanuri.auth.presentation.dto;

import org.springframework.web.multipart.MultipartFile;
import team.hackerping.nanuri.auth.application.dto.command.AuthCommand;
import team.hackerping.nanuri.user.domain.UserType;

public class AuthRequest {
    public record Signup(
            String username,
            String password,
            UserType userType,
            MultipartFile enrollmentProofImage
    ) {
        public AuthCommand.Signup toCommand() {
            return AuthCommand.Signup.of(username, password, userType, enrollmentProofImage);
        }
    }
}