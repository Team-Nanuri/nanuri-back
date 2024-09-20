package team.hackerping.nanuri.auth.application.dto;

import team.hackerping.nanuri.user.domain.User;
import team.hackerping.nanuri.user.domain.UserType;

public class AuthInfo {
    public record UserInfo(
            Long userId,
            UserType userType,
            String profileImageUrl
    ) {
        public static UserInfo from(User user) {
            return new UserInfo(
                    user.getId(),
                    user.getUserType(),
                    user.getProfileImageUrl()
            );
        }
    }
}