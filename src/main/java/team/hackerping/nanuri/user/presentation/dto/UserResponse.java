package team.hackerping.nanuri.user.presentation.dto;

import team.hackerping.nanuri.user.domain.User;
import team.hackerping.nanuri.user.domain.UserType;

public class UserResponse {

    public record UserInfo(
            Long id,
            String username,
            UserType userType
    ){
    }

    public record MaskedUserInfo(
            Long id,
            String username,
            UserType userType
    ){
    }
}