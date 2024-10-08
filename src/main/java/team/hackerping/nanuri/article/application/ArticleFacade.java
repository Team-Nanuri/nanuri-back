package team.hackerping.nanuri.article.application;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.util.Pair;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import team.hackerping.nanuri.article.application.command.ChangeStatusCommand;
import team.hackerping.nanuri.article.application.command.PagingArticleCommand;
import team.hackerping.nanuri.article.application.command.RegisterArticleCommand;
import team.hackerping.nanuri.article.application.command.UpdateArticleCommand;
import team.hackerping.nanuri.article.application.info.ArticleInfo;
import team.hackerping.nanuri.article.domain.Article;
import team.hackerping.nanuri.global.annotation.Facade;

@Transactional
@Facade
@RequiredArgsConstructor
public class ArticleFacade {
    private final ArticleService articleService;
    private final LikeService likeService;


    @PreAuthorize("hasRole('ACTIVATED')")
    public ArticleInfo.Detail registerArticle(RegisterArticleCommand command) {
        Article article = articleService.registerArticle(command);
        Boolean isLike = likeService.searchLike(command.getWriterId(), article.getId());

        return ArticleInfo.Detail.of(article, isLike);
    }

    @PreAuthorize("hasRole('ACTIVATED')")
    public ArticleInfo.Detail changeArticleStatus(ChangeStatusCommand command) {
        Article article = articleService.changeArticleStatus(command);
        Boolean isLike = likeService.searchLike(command.getUserId(), article.getId());

        return ArticleInfo.Detail.of(article, isLike);
    }

    @PreAuthorize("hasRole('ACTIVATED')")
    @Transactional(readOnly = true)
    public ArticleInfo.Detail searchArticle(Long userId, Long articleId) {
        Article article = articleService.searchArticle(articleId);
        Boolean isLike = likeService.searchLike(userId, articleId);

        return ArticleInfo.Detail.of(article, isLike);
    }

    @Transactional(readOnly = true)
    public ArticleInfo.Paging searchArticles(PagingArticleCommand command) {

        Page<Article> articlePage = articleService.searchArticles(command);
        List<Pair<Article, Boolean>> pairs = likeService.searchLikes(command.getUserId(), articlePage.getContent());

        return ArticleInfo.Paging.of(articlePage, pairs);
    }

    public void deleteArticle(Long userId, Long articleId) {
        articleService.deleteArticle(userId, articleId);
    }

    @PreAuthorize("hasRole('ACTIVATED')")
    public ArticleInfo.Detail updateArticle(UpdateArticleCommand command) {
        Article article = articleService.updateArticle(command);
        ArticleInfo.Detail.of(article, false);

        return ArticleInfo.Detail.of(article, false);
    }
}
