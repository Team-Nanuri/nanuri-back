package team.hackerping.nanuri.article.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import team.hackerping.nanuri.article.domain.Article;

public interface ArticleRepository extends JpaRepository<Article, Long> {
}
