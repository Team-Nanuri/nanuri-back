package team.hackerping.nanuri.article.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import team.hackerping.nanuri.article.domain.Article;
import team.hackerping.nanuri.article.domain.ArticleImage;
import team.hackerping.nanuri.article.persistence.ArticleRepository;
import team.hackerping.nanuri.global.exception.NanuriException;
import team.hackerping.nanuri.global.exception.code.GeneralError;
import team.hackerping.nanuri.user.domain.User;
import team.hackerping.nanuri.user.persistence.UserRepository;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ArticleService {
    private final ArticleRepository articleRepository;
    private final UserRepository userRepository;

    public ArticleInfo.Detail registerArticle(RegisterArticleCommand command) {

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

        return ArticleInfo.Detail.of(article, false);
    }
}
