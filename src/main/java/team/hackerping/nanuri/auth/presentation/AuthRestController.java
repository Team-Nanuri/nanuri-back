package team.hackerping.nanuri.auth.presentation;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import team.hackerping.nanuri.auth.presentation.dto.AuthRequest;

@RestController
@RequestMapping("/api/auth")
public class AuthRestController implements AuthController {

    @Override
    @PostMapping("/signup")
    @ResponseStatus(HttpStatus.CREATED)
    public void signup(AuthRequest request) {
        //Todo
    }
}
