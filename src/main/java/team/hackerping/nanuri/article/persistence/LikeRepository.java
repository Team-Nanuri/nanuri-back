package team.hackerping.nanuri.article.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import team.hackerping.nanuri.article.domain.Like;

import java.util.List;

public interface LikeRepository extends JpaRepository<Like, Long> {
    Boolean existsByUserIdAndArticleId(Long userId, Long articleId);

    List<Like> findAllByUserId(Long userId);
}
