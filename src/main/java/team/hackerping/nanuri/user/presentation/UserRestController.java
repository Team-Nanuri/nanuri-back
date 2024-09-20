package team.hackerping.nanuri.user.presentation;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import team.hackerping.nanuri.user.presentation.dto.UserResponse.MaskedUserInfo;
import team.hackerping.nanuri.user.presentation.dto.UserResponse.UserInfo;

@RestController
@RequestMapping("/api/users")
public class UserRestController implements UserController{

    @Override
    @GetMapping("/me")
    public ResponseEntity<UserInfo> getMyInfo() {
        // TODO
        return null;
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<MaskedUserInfo> getOtherInfo(@PathVariable Long id) {
        // TODO
        return null;
    }
}
