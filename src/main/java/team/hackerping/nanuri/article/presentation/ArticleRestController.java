package team.hackerping.nanuri.article.presentation;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import team.hackerping.nanuri.article.application.ArticleInfo;
import team.hackerping.nanuri.article.application.ArticleService;
import team.hackerping.nanuri.article.presentation.dto.ArticleResponse;
import team.hackerping.nanuri.article.presentation.dto.ArticlePagingParams;
import team.hackerping.nanuri.article.presentation.dto.ArticleRequest.Status;
import team.hackerping.nanuri.article.presentation.dto.ArticleRequest.Upsert;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/article")
public class ArticleRestController implements ArticleController{

    private final ArticleService articleService;

    @Override
    @GetMapping()
    public ResponseEntity<ArticleResponse.Paging> getArticles(
            @PageableDefault Pageable pageable,
            ArticlePagingParams params
    ) {
        return null;
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<ArticleResponse.Detail> getArticle(@PathVariable Long id) {
        return null;
    }

    @Override
    @PostMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<ArticleResponse.Detail> createArticle(@ModelAttribute Upsert request) {
        // TODO: user id를 access token에서 추출한 정보로 수정
        Long userId = 1L;

        ArticleInfo.Detail info = articleService.registerArticle(request.toCommand(userId));

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
        //Todo
        return null;
    }

    @Override
    @DeleteMapping("/{id}")
    public void deleteArticle(@PathVariable Long id) {
        //Todo
    }
}
