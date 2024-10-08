package team.hackerping.nanuri.article.application;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;
import team.hackerping.nanuri.article.application.command.PagingArticleByLikeCommand;
import team.hackerping.nanuri.article.application.info.ArticleInfo;
import team.hackerping.nanuri.article.domain.Article;
import team.hackerping.nanuri.article.domain.Like;
import team.hackerping.nanuri.article.persistence.ArticleLikeQueryRepository;
import team.hackerping.nanuri.article.persistence.ArticleRepository;
import team.hackerping.nanuri.article.persistence.LikeRepository;
import team.hackerping.nanuri.global.exception.NanuriException;
import team.hackerping.nanuri.global.exception.code.ArticleError;
import team.hackerping.nanuri.global.exception.code.GeneralError;
import team.hackerping.nanuri.user.domain.User;
import team.hackerping.nanuri.user.persistence.UserRepository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
@Service
public class LikeService {

    private final LikeRepository likeRepository;
    private final UserRepository userRepository;
    private final ArticleRepository articleRepository;
    private final ArticleLikeQueryRepository articleLikeQueryRepository;

    public Boolean searchLike(Long userId, Long articleId) {
        return likeRepository.existsByUserIdAndArticleId(userId, articleId);
    }

    public List<Pair<Article, Boolean>> searchLikes(Long userId, List<Article> articles) {
        Set<Long> likes = new HashSet<>(
                likeRepository.findAllByUserId(userId).stream()
                    .map(like -> like.getArticle().getId())
                    .toList());

        List<Pair<Article, Boolean>> result = new ArrayList<>();
        for (Article article : articles) {
            if (likes.contains(article.getId())) {
                result.add(Pair.of(article, true));
            } else {
                result.add(Pair.of(article, false));
            }
        }

        return result;
    }

    public void likeArticle(Long userId, Long articleId) {
        User user = userRepository.getReferenceById(userId);
        Article article = articleRepository.findById(articleId)
                .orElseThrow(() -> new NanuriException(GeneralError.NOT_FOUND, "게시글"));

        if (user.equals(article.getWriter()))
            throw new NanuriException(ArticleError.CANNOT_LIKE_MY_ARTICLE);

        if (likeRepository.existsByUserAndArticle(user, article))
            throw new NanuriException(ArticleError.ALREADY_LIKED);

        likeRepository.save(Like.of(user, article));
    }

    public void unlikeArticle(Long userId, Long articleId) {
        User user = userRepository.getReferenceById(userId);
        Article article = articleRepository.findById(articleId)
                .orElseThrow(() -> new NanuriException(GeneralError.NOT_FOUND, "게시글"));

        Like like = likeRepository.findByUserAndArticle(user, article)
                .orElseThrow(() -> new NanuriException(GeneralError.NOT_FOUND, "좋아요"));

        likeRepository.delete(like);
    }

    public ArticleInfo.Paging myLikes(PagingArticleByLikeCommand command) {
        Page<Article> page = articleLikeQueryRepository.pagingArticleByLike(command.getUserId(), command.getPageable());

        return ArticleInfo.Paging.of(page, searchLikes(command.getUserId(), page.getContent()));
    }
}
