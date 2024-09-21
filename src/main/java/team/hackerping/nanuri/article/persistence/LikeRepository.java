package team.hackerping.nanuri.article.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import team.hackerping.nanuri.article.domain.Like;

public interface LikeRepository extends JpaRepository<Like, Long> {
    Boolean existsByUserIdAndArticleId(Long userId, Long articleId);
}
