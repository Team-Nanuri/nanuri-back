package team.hackerping.nanuri.article.presentation;

import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import team.hackerping.nanuri.article.presentation.dto.ArticleResponse;
import team.hackerping.nanuri.article.presentation.dto.ArticlePagingParams;
import team.hackerping.nanuri.article.presentation.dto.ArticleRequest.Status;
import team.hackerping.nanuri.article.presentation.dto.ArticleRequest.Upsert;

@RestController
@RequestMapping("/api/article")
public class ArticleRestController implements ArticleController{

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
    @PostMapping()
    public ResponseEntity<ArticleResponse.Detail> createArticle(@RequestBody Upsert request) {
        //Todo
        return null;
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
