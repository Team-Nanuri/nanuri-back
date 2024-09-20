package team.hackerping.nanuri.user.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team.hackerping.nanuri.global.exception.NanuriException;
import team.hackerping.nanuri.global.exception.code.GeneralError;
import team.hackerping.nanuri.user.domain.UserInfo;
import team.hackerping.nanuri.user.domain.User;
import team.hackerping.nanuri.user.persistence.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserInfo getUserInfo(Long userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NanuriException(GeneralError.NOT_FOUND, "사용자가 존재하지 않습니다."));

        return UserInfo.from(user);
    }
}
