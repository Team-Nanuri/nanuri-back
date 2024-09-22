package team.hackerping.nanuri.user.presentation;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import team.hackerping.nanuri.user.application.UserService;
import team.hackerping.nanuri.user.application.UserInfo;
import team.hackerping.nanuri.user.presentation.dto.UserResponse;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/users")
public class UserRestController implements UserController{

    private final UserService userService;

    @Override
    @GetMapping("/me")
    public ResponseEntity<UserResponse.UserDto> getMyInfo() {

        Long userId = Long.parseLong(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());

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
