package team.hackerping.nanuri.article.application;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import team.hackerping.nanuri.article.application.command.ChangeStatusCommand;
import team.hackerping.nanuri.article.application.command.PagingArticleCommand;
import team.hackerping.nanuri.article.application.command.RegisterArticleCommand;
import team.hackerping.nanuri.article.application.command.UpdateArticleCommand;
import team.hackerping.nanuri.article.domain.Article;
import team.hackerping.nanuri.article.domain.ArticleImage;
import team.hackerping.nanuri.article.persistence.ArticleJpaQueryRepository;
import team.hackerping.nanuri.article.persistence.ArticleRepository;
import team.hackerping.nanuri.global.exception.NanuriException;
import team.hackerping.nanuri.global.exception.code.ArticleError;
import team.hackerping.nanuri.global.exception.code.GeneralError;
import team.hackerping.nanuri.user.domain.User;
import team.hackerping.nanuri.user.persistence.UserRepository;

import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
@Service
public class ArticleService {
    private final ArticleRepository articleRepository;
    private final ArticleJpaQueryRepository articleJpaQueryRepository;
    private final UserRepository userRepository;

    public Article registerArticle(RegisterArticleCommand command) {

        User user = userRepository.findById(command.getWriterId())
                .orElseThrow(() -> new NanuriException(GeneralError.NOT_FOUND, "사용자"));

        // TODO: S3에 이미지 업로드
        List<String> imgUrls = List.of("11111", "22222", "33333");
        ArticleImage image = ArticleImage.of(imgUrls);

        Article article = Article.of(
                command.getTitle(),
                command.getContent(),
                command.getCategory(),
                command.getShareType(),
                command.getRentalStartDate(),
                command.getRentalEndDate(),
                image,
                user
        );

        articleRepository.save(article);

        return article;
    }

    public Article changeArticleStatus(ChangeStatusCommand command) {
        Article article = articleRepository.findById(command.getArticleId())
                .orElseThrow(() -> new NanuriException(GeneralError.NOT_FOUND, "게시글"));

        if (article.getWriter().getId() != command.getUserId())
            throw new NanuriException(ArticleError.UPDATE_FORBIDDEN, "본인의 게시글이 아닙니다.");

        article.changeStatus(command.getStatus());

        return article;
    }

    public Article searchArticle(Long articleId) {
        return articleRepository.findById(articleId)
                .orElseThrow(() -> new NanuriException(GeneralError.NOT_FOUND, "게시글"));
    }

    public Page<Article> searchArticles(PagingArticleCommand command) {
        return articleJpaQueryRepository.pagingByCriteria(command);
    }

    public Article updateArticle(UpdateArticleCommand command) {
        Article article = articleRepository.findById(command.getArticleId())
                .orElseThrow(() -> new NanuriException(GeneralError.NOT_FOUND, "게시글"));

        if (article.getWriter().getId() != command.getUserId())
            throw new NanuriException(ArticleError.UPDATE_FORBIDDEN, "본인의 게시글이 아닙니다.");

        // TODO: S3에 업로드 및 기존 사진 삭제
        List<String> imgUrls = List.of("aaaaaa", "bbbbbb", "cccccc");
        ArticleImage image = ArticleImage.of(imgUrls);

        article.update(
                command.getTitle(),
                command.getContent(),
                command.getCategory(),
                command.getShareType(),
                command.getRentalStartDate(),
                command.getRentalEndDate(),
                image
        );

        return article;
    }

    public void deleteArticle(Long userId, Long articleId) {
        Article article = articleRepository.findById(articleId)
                .orElseThrow(() -> new NanuriException(GeneralError.NOT_FOUND, "게시글"));

        if (!Objects.equals(article.getWriter().getId(), userId))
            throw new NanuriException(ArticleError.UPDATE_FORBIDDEN, "본인의 게시글이 아닙니다.");

        articleRepository.deleteById(articleId);
    }
}
