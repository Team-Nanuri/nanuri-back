package team.hackerping.nanuri.article.persistence;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import team.hackerping.nanuri.article.domain.Article;
import team.hackerping.nanuri.article.domain.QArticle;
import team.hackerping.nanuri.article.domain.QLike;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class ArticleLikeQueryRepository {
    private final JPAQueryFactory queryFactory;

    public Page<Article> pagingArticleByLike(Long userId, Pageable pageable) {

        List<Article> query = queryFactory
                .select(QArticle.article)
                .from(QArticle.article)
                .join(QLike.like)
                .on(QArticle.article.id.eq(QLike.like.article.id))
                .where(QLike.like.user.id.eq(userId))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(QArticle.article.createdAt.desc())
                .fetch();

        Long total = queryFactory
                .select(QArticle.article.count())
                .from(QArticle.article)
                .join(QLike.like)
                .on(QArticle.article.id.eq(QLike.like.article.id))
                .where(QLike.like.user.id.eq(userId))
                .fetchOne();

        return new PageImpl<>(query, pageable, total == null ? 0 : total);
    }
}
