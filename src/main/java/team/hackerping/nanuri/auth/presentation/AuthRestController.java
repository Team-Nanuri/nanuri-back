package team.hackerping.nanuri.auth.presentation;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import team.hackerping.nanuri.auth.application.AuthFacade;
import team.hackerping.nanuri.auth.presentation.dto.AuthRequest;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthRestController implements AuthController {
    private final AuthFacade authFacade;

    @Override
    @PostMapping("/signup")
    @ResponseStatus(HttpStatus.CREATED)
    public void signup(AuthRequest.Signup request) {
        authFacade.signup(request.toCommand());
    }
}