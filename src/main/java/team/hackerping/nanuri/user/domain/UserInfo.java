package team.hackerping.nanuri.user.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UserInfo {

    private final Long id;
    private final String username;
    private final UserType userType;

    public static UserInfo from(User user) {
        return new UserInfo(user.getId(), user.getUsername(), user.getUserType());
    }
}
