package team.hackerping.nanuri.user.presentation;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import team.hackerping.nanuri.user.application.UserService;
import team.hackerping.nanuri.user.domain.UserInfo;
import team.hackerping.nanuri.user.presentation.dto.UserResponse;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/users")
public class UserRestController implements UserController{

    private final UserService userService;

    @Override
    @GetMapping("/me")
    public ResponseEntity<UserResponse.UserDto> getMyInfo() {
        // AccessToken에서 값을 가져오는 것으로 수정
        Long userId = 1L;

        UserInfo info = userService.getUserInfo(userId);
        UserResponse.UserDto response = UserResponse.UserDto.from(info);

        return ResponseEntity.ok(response);
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<UserResponse.MaskedUserDto> getOtherInfo(@PathVariable Long id) {

        UserInfo info = userService.getUserInfo(id);
        UserResponse.MaskedUserDto response = UserResponse.MaskedUserDto.from(info);

        return ResponseEntity.ok(response);
    }
}
