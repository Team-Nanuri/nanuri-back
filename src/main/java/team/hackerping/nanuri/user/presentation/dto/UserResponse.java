package team.hackerping.nanuri.user.presentation.dto;

import team.hackerping.nanuri.user.domain.User;
import team.hackerping.nanuri.user.domain.UserType;

public class UserResponse {

    public record UserInfo(
            Long id,
            String username,
            UserType userType
    ){
        static public UserInfo from(User user) {
            return new UserInfo(
                    user.getId(),
                    user.getUsername(),
                    user.getUserType()
            );
        }
    }

    public record MaskedUserInfo(
            Long id,
            String username,
            UserType userType
    ){
        static public MaskedUserInfo from(User user) {
            return new MaskedUserInfo(
                    user.getId(),
                    user.getMaskedUsername(),
                    user.getUserType()
            );
        }
    }
}