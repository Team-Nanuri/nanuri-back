package team.hackerping.nanuri.auth.application;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import team.hackerping.nanuri.auth.application.dto.command.AuthCommand;
import team.hackerping.nanuri.global.annotation.Facade;
import team.hackerping.nanuri.notice.application.NoticeService;

@Facade
@Slf4j
@RequiredArgsConstructor
public class AuthFacade {
    private final AuthService authService;
    private final NoticeService noticeService;

    @Transactional
    public void signup(AuthCommand.Signup command) {
        var userId = authService.signup(command);
        noticeService.sendAuthStartNotice(userId);

        var response = authService.verifyProfile(userId);
        response.thenAccept(isSuccess -> {
            log.info("User verification result: {}", isSuccess);
            noticeService.sendAuthNotice(userId, isSuccess);
        });
    }
}