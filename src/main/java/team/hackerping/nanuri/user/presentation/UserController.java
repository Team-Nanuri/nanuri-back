package team.hackerping.nanuri.user.presentation;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import team.hackerping.nanuri.user.presentation.dto.UserResponse;

@Tag(name = "User", description = "유저 API")
public interface UserController {
    @Operation(summary = "내 정보 조회")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "성공")
            }
    )
    ResponseEntity<UserResponse.UserInfo> getMyInfo();

    @Operation(summary = "상대 정보 조회")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "성공")
            }
    )
    ResponseEntity<UserResponse.MaskedUserInfo> getOtherInfo(@PathVariable Long id);
}
