package team.hackerping.nanuri.article.application;

import lombok.RequiredArgsConstructor;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;
import team.hackerping.nanuri.article.domain.Article;
import team.hackerping.nanuri.article.persistence.LikeRepository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
@Service
public class LikeService {

    private final LikeRepository likeRepository;

    public Boolean searchLike(Long userId, Long articleId) {
        return likeRepository.existsByUserIdAndArticleId(userId, articleId);
    }

    public List<Pair<Article, Boolean>> searchLikes(Long userId, List<Article> articles) {
        Set<Long> likes = new HashSet<>(
                likeRepository.findAllByUserId(userId).stream()
                    .map(like -> like.getArticle().getId())
                    .toList());

        List<Pair<Article, Boolean>> result = new ArrayList<>();
        for (Article article : articles) {
            if (likes.contains(article.getId())) {
                result.add(Pair.of(article, true));
            } else {
                result.add(Pair.of(article, false));
            }
        }

        return result;
    }
}
