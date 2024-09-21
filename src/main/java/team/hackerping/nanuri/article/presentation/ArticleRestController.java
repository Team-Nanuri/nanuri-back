package team.hackerping.nanuri.article.presentation;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import team.hackerping.nanuri.article.application.ArticleFacade;
import team.hackerping.nanuri.article.application.command.ChangeStatusCommand;
import team.hackerping.nanuri.article.application.command.PagingArticleCommand;
import team.hackerping.nanuri.article.application.info.ArticleInfo;
import team.hackerping.nanuri.article.presentation.dto.ArticleResponse;
import team.hackerping.nanuri.article.presentation.dto.ArticlePagingParams;
import team.hackerping.nanuri.article.presentation.dto.ArticleRequest.Status;
import team.hackerping.nanuri.article.presentation.dto.ArticleRequest.Upsert;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/articles")
public class ArticleRestController implements ArticleController{

    private final ArticleFacade articleFacade;

    @Override
    @GetMapping()
    public ResponseEntity<ArticleResponse.Paging> getArticles(
            @PageableDefault Pageable pageable,
            ArticlePagingParams params
    ) {
        //TODO: user id를 access token에서 추출한 정보로 수정
        Long userId = 1L;

        PagingArticleCommand command = new PagingArticleCommand(
                userId,
                pageable,
                params.writerId(),
                params.categories(),
                params.keyword(),
                params.shareType(),
                params.status(),
                params.sort()
        );

        ArticleInfo.Paging info = articleFacade.searchArticles(command);

        return ResponseEntity.status(HttpStatus.OK).body(ArticleResponse.Paging.of(info));
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<ArticleResponse.Detail> getArticle(@PathVariable Long id) {
        // TODO: user id를 access token에서 추출한 정보로 수정
        Long userId = 1L;

        ArticleInfo.Detail info = articleFacade.searchArticle(userId, id);

        return ResponseEntity.status(HttpStatus.OK).body(ArticleResponse.Detail.from(info));
    }

    @Override
    @PostMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<ArticleResponse.Detail> createArticle(@ModelAttribute Upsert request) {
        // TODO: user id를 access token에서 추출한 정보로 수정
        Long userId = 1L;

        ArticleInfo.Detail info = articleFacade.registerArticle(request.toCommand(userId));

        return ResponseEntity.status(HttpStatus.CREATED).body(ArticleResponse.Detail.from(info));
    }

    @Override
    @PutMapping
    public ResponseEntity<ArticleResponse.Detail> modifyArticle(@RequestBody Upsert request) {
        //Todo
        return null;
    }

    @Override
    @PatchMapping("/{id}")
    public ResponseEntity<ArticleResponse.Detail> changeArticleStatus(
            @PathVariable Long id,
            @RequestBody Status request) {

        // TODO: user id를 access token에서 추출한 정보로 수정
        Long userId = 1L;

        ChangeStatusCommand command = new ChangeStatusCommand(userId, id, request.status());
        ArticleInfo.Detail info = articleFacade.changeArticleStatus(command);

        return ResponseEntity.status(HttpStatus.OK).body(ArticleResponse.Detail.from(info));
    }

    @Override
    @DeleteMapping("/{id}")
    public void deleteArticle(@PathVariable Long id) {
        //Todo: user id를 access token에서 추출한 정보로 수정
        Long userId = 1L;

        articleFacade.deleteArticle(userId, id);
    }
}
