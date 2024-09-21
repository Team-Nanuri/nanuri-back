package team.hackerping.nanuri.article.application;

import lombok.RequiredArgsConstructor;
import team.hackerping.nanuri.article.application.command.RegisterArticleCommand;
import team.hackerping.nanuri.article.application.info.ArticleInfo;
import team.hackerping.nanuri.article.domain.Article;
import team.hackerping.nanuri.global.annotation.Facade;

@Facade
@RequiredArgsConstructor
public class ArticleFacade {
    private final ArticleService articleService;
    private final LikeService likeService;

    public ArticleInfo.Detail registerArticle(RegisterArticleCommand command) {
        Article article = articleService.registerArticle(command);
        Boolean isLike = likeService.searchLike(command.getWriterId(), article.getId());

        return ArticleInfo.Detail.of(article, isLike);
    }
}
