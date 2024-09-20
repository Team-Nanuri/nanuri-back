package team.hackerping.nanuri.auth.application;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.hackerping.nanuri.auth.application.dto.AuthInfo;
import team.hackerping.nanuri.auth.application.dto.command.AuthCommand;
import team.hackerping.nanuri.user.domain.User;
import team.hackerping.nanuri.user.persistence.UserRepository;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public AuthInfo.UserInfo signup(AuthCommand.Signup command) {
        var username = command.username();
        var encodedPassword = passwordEncoder.encode(command.password());
        var userType = command.userType();

        var user = User.of(username, encodedPassword, userType);
        //Todo: S3에 이미지 업로드 후 저장
        //Todo: User에 이미지 경로 저장

        userRepository.save(user);

        return AuthInfo.UserInfo.from(user);
    }
}