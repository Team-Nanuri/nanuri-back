package team.hackerping.nanuri.auth.application;

import lombok.RequiredArgsConstructor;
import team.hackerping.nanuri.auth.application.dto.command.AuthCommand;
import team.hackerping.nanuri.global.annotation.Facade;

@Facade
@RequiredArgsConstructor
public class AuthFacade {
    private final AuthService authService;

    public void signup(AuthCommand.Signup command) {
        var response = authService.signup(command);
        //Todo: 인증 로직 -> 비동기 처리
    }
}