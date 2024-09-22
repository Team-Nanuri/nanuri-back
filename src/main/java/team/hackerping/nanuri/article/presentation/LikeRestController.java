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
        // TODO: user id를 access token에서 추출한 정보로 수정
        Long userId = 1L;

        likeService.likeArticle(userId, articleId);
    }

    @Override
    @DeleteMapping
    public void unlikeArticle(Long articleId) {
        // TODO: 좋아요 취소 기능 구현
    }
}
