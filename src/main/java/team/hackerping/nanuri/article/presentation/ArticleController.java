package team.hackerping.nanuri.article.presentation;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import team.hackerping.nanuri.article.presentation.dto.ArticleResponse;
import team.hackerping.nanuri.article.presentation.dto.ArticlePagingParams;
import team.hackerping.nanuri.article.presentation.dto.ArticleRequest;

@Tag(name = "Article", description = "게시글 관련 API")
public interface ArticleController {

    @Operation(summary = "게시글 목록 조회")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "게시글 목록 조회 성공"),
            }
    )
    ResponseEntity<ArticleResponse.Paging> getArticles(
            @ParameterObject Pageable pageable,
            @ParameterObject ArticlePagingParams params
    );

    @Operation(summary = "게시글 상세 조회")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "게시글 상세 조회 성공"),
            }
    )
    ResponseEntity<ArticleResponse.Detail> getArticle(@PathVariable Long id);

    @Operation(summary = "게시글 작성")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "201", description = "게시글 작성 성공"),
            }
    )
    ResponseEntity<ArticleResponse.Detail> createArticle(@ModelAttribute ArticleRequest.Upsert request);

    @Operation(summary = "게시글 수정")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "게시글 수정 성공"),
            }
    )
    ResponseEntity<ArticleResponse.Detail> modifyArticle(
            @RequestBody(description = "게시글 수정 정보") ArticleRequest.Upsert request
    );

    @Operation(summary = "게시글 상태 변경")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "게시글 상태 변경 성공"),
            }
    )
    ResponseEntity<ArticleResponse.Detail> changeArticleStatus(
            @PathVariable Long id,
            @RequestBody(description = "변경할 상태") ArticleRequest.Status request
    );

    @Operation(summary = "게시글 삭제")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "게시글 삭제 성공"),
            }
    )
    void deleteArticle(@PathVariable Long id);
}
