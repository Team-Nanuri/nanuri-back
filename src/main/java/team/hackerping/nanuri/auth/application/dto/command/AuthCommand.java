package team.hackerping.nanuri.auth.application.dto.command;

import org.springframework.web.multipart.MultipartFile;
import team.hackerping.nanuri.user.domain.UserType;

public class AuthCommand {
    public record Signup(
            String username,
            String password,
            UserType userType,
            MultipartFile profileImage
    ) {
        public static Signup of(String username,
                                String password,
                                UserType userType,
                                MultipartFile profileImage
        ) {
            return new Signup(username, password, userType, profileImage);
        }
    }
}
