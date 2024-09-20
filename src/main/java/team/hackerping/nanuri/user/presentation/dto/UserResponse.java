package team.hackerping.nanuri.user.presentation.dto;

import team.hackerping.nanuri.user.domain.UserInfo;
import team.hackerping.nanuri.user.domain.UserType;

public class UserResponse {

    public record UserDto(
            Long id,
            String username,
            UserType userType
    ){
        static public UserDto from(UserInfo user) {
            return new UserDto(
                    user.getId(),
                    user.getUsername(),
                    user.getUserType()
            );
        }
    }

    public record MaskedUserDto(
            Long id,
            String username,
            UserType userType
    ){
        static public MaskedUserDto from(UserInfo info) {
            String username = info.getUsername();
            return new MaskedUserDto(
                    info.getId(),
                    username.substring(0, 2) + "*".repeat(Math.max(0, username.length() - 2)),
                    info.getUserType()
            );
        }
    }
}