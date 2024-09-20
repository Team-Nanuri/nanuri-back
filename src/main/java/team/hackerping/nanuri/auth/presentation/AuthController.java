package team.hackerping.nanuri.auth.presentation;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.ModelAttribute;
import team.hackerping.nanuri.auth.presentation.dto.AuthRequest;

@Tag(name = "Auth", description = "인증 관련 API")
public interface AuthController {

    @Operation(summary = "회원가입")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "회원가입 성공"),
                    @ApiResponse(responseCode = "400", description = "회원가입 실패")
            }
    )
    void signup(@ModelAttribute AuthRequest request);
}
