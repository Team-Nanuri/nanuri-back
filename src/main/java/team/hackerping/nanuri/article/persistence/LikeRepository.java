package team.hackerping.nanuri.article.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import team.hackerping.nanuri.article.domain.Article;
import team.hackerping.nanuri.article.domain.Like;
import team.hackerping.nanuri.user.domain.User;

import java.util.List;

public interface LikeRepository extends JpaRepository<Like, Long> {
    Boolean existsByUserIdAndArticleId(Long userId, Long articleId);

    List<Like> findAllByUserId(Long userId);

    Boolean existsByUserAndArticle(User user, Article article);
}
