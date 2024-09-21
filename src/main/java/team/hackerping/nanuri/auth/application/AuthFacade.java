package team.hackerping.nanuri.auth.application;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import team.hackerping.nanuri.auth.application.dto.command.AuthCommand;
import team.hackerping.nanuri.global.annotation.Facade;

@Facade
@Slf4j
@RequiredArgsConstructor
public class AuthFacade {
    private final AuthService authService;

    @Transactional
    public void signup(AuthCommand.Signup command) {
        var userId = authService.signup(command);
        //Todo: 인증 예정 메시지 보내기

        var response = authService.verifyProfile(userId);
        response.thenAccept(isSuccess ->
                        log.info("User verification result: {}", isSuccess)
                //Todo: 인증 결과 메시지 보내기
        );
    }
}