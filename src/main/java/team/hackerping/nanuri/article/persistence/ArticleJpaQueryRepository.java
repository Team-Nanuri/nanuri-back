package team.hackerping.nanuri.article.persistence;

import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;
import team.hackerping.nanuri.article.application.command.PagingArticleCommand;
import team.hackerping.nanuri.article.domain.*;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class ArticleJpaQueryRepository {

    private final JPAQueryFactory queryFactory;

    public Page<Article> pagingByCriteria(PagingArticleCommand command) {
        List<Article> query = queryFactory
                .select(QArticle.article)
                .from(QArticle.article)
                .where(
                        inTitle(command.getKeyword()),
                        eqWriterId(command.getWriterId()),
                        inCategories(command.getCategories()),
                        eqShareType(command.getShareType()),
                        eqStatus(command.getStatus())
                )
                .orderBy(orderSpecifier(command.getSort()))
                .offset(command.getPageable().getOffset())
                .limit(command.getPageable().getPageSize())
                .fetch();

        Long total = queryFactory
                .select(QArticle.article.count())
                .from(QArticle.article)
                .where(
                        inTitle(command.getKeyword()),
                        eqWriterId(command.getWriterId()),
                        inCategories(command.getCategories()),
                        eqShareType(command.getShareType()),
                        eqStatus(command.getStatus())
                )
                .fetchOne();

        return new PageImpl<>(query, command.getPageable(), total == null ? 0 : total);
    }

    private BooleanExpression inTitle(String title) {
        return StringUtils.hasText(title) ? QArticle.article.title.in(title) : null;
    }

    private BooleanExpression eqWriterId(Long writerId) {
        return writerId != null ? QArticle.article.writer.id.eq(writerId) : null;
    }

    private BooleanExpression inCategories(List<ItemCategory> categories) {
        return categories != null ? QArticle.article.itemCategory.in(categories) : null;
    }

    private BooleanExpression eqShareType(ShareType shareType) {
        return shareType != null ? QArticle.article.shareType.eq(shareType) : null;
    }

    private BooleanExpression eqStatus(ArticleStatus status) {
        return status != null ? QArticle.article.status.eq(status) : null;
    }

    private OrderSpecifier orderSpecifier(Sort sort) {
        if (sort == Sort.RENTAL_START_DATE_ASC)
            return QArticle.article.rentalStartDate.asc();
        else if (sort == Sort.RENTAL_START_DATE_DESC)
            return QArticle.article.rentalStartDate.desc();
        else if (sort == Sort.CREATED_AT_ASC)
            return QArticle.article.createdAt.asc();
        else
            return QArticle.article.createdAt.desc();
    }
}
