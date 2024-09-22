package team.hackerping.nanuri.article.presentation;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import team.hackerping.nanuri.article.application.LikeService;

@RequiredArgsConstructor
@RequestMapping("/api/articles/{id}/likes")
@RestController
public class LikeRestController implements LikeController {

    private final LikeService likeService;

    @Override
    @PostMapping
    public void likeArticle(Long articleId) {
        // TODO: 좋아요 기능 구현
    }

    @Override
    @DeleteMapping
    public void unlikeArticle(Long articleId) {
        // TODO: 좋아요 취소 기능 구현
    }
}
