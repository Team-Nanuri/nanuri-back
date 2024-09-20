package team.hackerping.nanuri.user.presentation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import team.hackerping.nanuri.user.application.UserService;
import team.hackerping.nanuri.user.presentation.dto.UserResponse.MaskedUserInfo;
import team.hackerping.nanuri.user.presentation.dto.UserResponse.UserInfo;

@RestController
@RequestMapping("/api/users")
public class UserRestController implements UserController{

    private final UserService userService;

    @Autowired
    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @Override
    @GetMapping("/me")
    public ResponseEntity<UserInfo> getMyInfo() {
        // AccessToken에서 값을 가져오는 것으로 수정
        Long userId = 1L;

        UserInfo info = userService.getUserInfo(userId);

        return ResponseEntity.ok(info);
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<MaskedUserInfo> getOtherInfo(@PathVariable Long id) {

        MaskedUserInfo info = userService.getOtherUserInfo(id);

        return ResponseEntity.ok(info);
    }
}
