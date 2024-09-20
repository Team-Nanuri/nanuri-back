package team.hackerping.nanuri.user.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team.hackerping.nanuri.global.exception.NanuriException;
import team.hackerping.nanuri.global.exception.code.GeneralError;
import team.hackerping.nanuri.user.domain.User;
import team.hackerping.nanuri.user.persistence.UserRepository;
import team.hackerping.nanuri.user.presentation.dto.UserResponse;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserResponse.UserInfo getUserInfo(Long userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NanuriException(GeneralError.NOT_FOUND, "사용자가 존재하지 않습니다."));

        return UserResponse.UserInfo.from(user);
    }

    public UserResponse.MaskedUserInfo getOtherUserInfo(Long userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NanuriException(GeneralError.NOT_FOUND, "사용자가 존재하지 않습니다."));

        return UserResponse.MaskedUserInfo.from(user);
    }
}
