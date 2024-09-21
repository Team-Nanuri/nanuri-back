package team.hackerping.nanuri.notice.presentation;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import team.hackerping.nanuri.notice.presentation.dto.NoticeResponse;

@Tag(name = "공지사항")
public interface NoticeController {

    @Operation(summary = "공지사항 조회")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "공지사항 조회 성공"),
    })
    ResponseEntity<List<NoticeResponse.Info>> getNotices(Authentication authentication);
}
