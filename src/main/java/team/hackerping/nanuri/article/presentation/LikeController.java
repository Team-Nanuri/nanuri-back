package team.hackerping.nanuri.article.presentation;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PathVariable;

@Tag(name = "Like", description = "좋아요 관련 API")
public interface LikeController {

    @Operation(summary = "게시글 좋아요")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "201", description = "게시글 좋아요 성공"),
            }
    )
    void likeArticle(@PathVariable Long articleId);

    @Operation(summary = "게시글 좋아요 취소")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "게시글 좋아요 취소 성공"),
            }
    )
    void unlikeArticle(@PathVariable Long articleId);
}
