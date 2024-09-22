package team.hackerping.nanuri.article.presentation;

import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import team.hackerping.nanuri.article.application.LikeService;
import team.hackerping.nanuri.article.application.command.PagingArticleByLikeCommand;
import team.hackerping.nanuri.article.application.info.ArticleInfo;
import team.hackerping.nanuri.article.presentation.dto.ArticleResponse;

@RequiredArgsConstructor
@RequestMapping("/api/articles")
@RestController
public class LikeRestController implements LikeController {

    private final LikeService likeService;

    @Override
    @PostMapping("/{id}/likes")
    public void likeArticle(@PathVariable Long id) {

        Long userId = Long.parseLong(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());

        likeService.likeArticle(userId, id);
    }

    @Override
    @DeleteMapping("/{id}/likes")
    public void unlikeArticle(@PathVariable Long id) {

        Long userId = Long.parseLong(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());

        likeService.unlikeArticle(userId, id);
    }

    @Override
    @GetMapping("/likes")
    public ResponseEntity<ArticleResponse.Paging> myLikes(
            @ParameterObject Pageable pageable
    ) {
        Long userId = Long.parseLong(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());

        ArticleInfo.Paging info = likeService.myLikes(new PagingArticleByLikeCommand(userId, pageable));

        return ResponseEntity.status(HttpStatus.OK).body(ArticleResponse.Paging.of(info));
    }
}
