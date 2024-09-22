package team.hackerping.nanuri.auth.application;

import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.hackerping.nanuri.auth.application.dto.command.AuthCommand;
import team.hackerping.nanuri.auth.client.OpenAiAuthClient;
import team.hackerping.nanuri.auth.persistence.ProfileS3Repository;
import team.hackerping.nanuri.user.domain.User;
import team.hackerping.nanuri.user.persistence.UserRepository;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ProfileS3Repository profileS3Repository;
    private final OpenAiAuthClient openAiAuthClient;

    @Transactional
    public Long signup(AuthCommand.Signup command) {
        var username = command.username();
        var encodedPassword = passwordEncoder.encode(command.password());
        var userType = command.userType();

        var user = User.of(username, encodedPassword, userType);
        var profileImageUrl = profileS3Repository.uploadProfileImage(username, command.profileImage());
        user.uploadProfileImage(profileImageUrl);
        userRepository.save(user);

        return user.getId();
    }

    @Async
    @Transactional
    public CompletableFuture<Boolean> verifyProfile(final Long userId) {
        var user = userRepository.findById(userId).orElseThrow();
        var profileImageUrl = user.getProfileImageUrl();

        var userType = openAiAuthClient.verifyProfile(profileImageUrl);
        if (Objects.isNull(userType)) {
            return CompletableFuture.completedFuture(false);
        }

        user.changeUserType(userType);
        user.activate();
        log.info("User {} is activated", user.getId());
        return CompletableFuture.completedFuture(true);
    }
}