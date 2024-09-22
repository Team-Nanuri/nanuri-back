package team.hackerping.nanuri.article.presentation;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import team.hackerping.nanuri.article.application.LikeService;

@RequiredArgsConstructor
@RequestMapping("/api/articles/{id}/likes")
@RestController
public class LikeRestController implements LikeController {

    private final LikeService likeService;

    @Override
    @PostMapping
    public void likeArticle(@PathVariable Long id) {

        Long userId = Long.parseLong(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());

        likeService.likeArticle(userId, id);
    }

    @Override
    @DeleteMapping
    public void unlikeArticle(@PathVariable Long id) {

        Long userId = Long.parseLong(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());

        likeService.unlikeArticle(userId, id);
    }
}
