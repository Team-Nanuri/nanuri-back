package team.hackerping.nanuri.article.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import team.hackerping.nanuri.article.persistence.LikeRepository;

@RequiredArgsConstructor
@Service
public class LikeService {

    private final LikeRepository likeRepository;

    public Boolean searchLike(Long userId, Long articleId) {
        return likeRepository.existsByUserIdAndArticleId(userId, articleId);
    }
}
