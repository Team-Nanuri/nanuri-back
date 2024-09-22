package team.hackerping.nanuri.translation.presentation;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import team.hackerping.nanuri.translation.presentation.dto.TranslationResponse;

@Tag(name = "번역", description = "번역 API")
public interface TranslationController {

    @Operation(summary = "검색어 추천")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "번역 성공"),
            @ApiResponse(responseCode = "400", description = "번역 실패")
    })
    ResponseEntity<TranslationResponse> translate(String text);
}
