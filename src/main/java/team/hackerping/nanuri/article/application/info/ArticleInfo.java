package team.hackerping.nanuri.article.application.info;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.util.Pair;
import team.hackerping.nanuri.article.domain.Article;
import team.hackerping.nanuri.article.domain.ArticleStatus;
import team.hackerping.nanuri.article.domain.ItemCategory;
import team.hackerping.nanuri.article.domain.ShareType;
import team.hackerping.nanuri.user.application.UserInfo;

@Slf4j
public class ArticleInfo {

    public record Paging(
            List<Basic> articles,
            int totalPage,
            int currentPage
    ) {
        public static Paging of(Page<Article> page, List<Pair<Article, Boolean>> pairs) {

            log.info("page: {}", page.getContent());
            log.info("pairs: {}", pairs);

            return new Paging(
                    pairs.stream()
                            .map(pair -> Basic.of(pair.getFirst(), pair.getSecond()))
                            .toList(),
                    page.getTotalPages(),
                    page.getPageable().getPageNumber()
            );
        }
    }

    public record Basic(
            Long articleId,
            String title,
            String content,
            ArticleStatus status,
            LocalDateTime createdAt,
            ShareType shareType,
            String imageUrl,
            Boolean liked
    ) {
        public static Basic of(Article article, Boolean liked) {
            return new Basic(
                    article.getId(),
                    article.getTitle(),
                    article.getContent(),
                    article.getStatus(),
                    article.getCreatedAt(),
                    article.getShareType(),
                    article.getFirstImageUrl(),
                    liked
            );
        }
    }

    public record Detail(
            Long id,
            String title,
            String content,
            LocalDateTime createdAt,
            ItemCategory itemCategory,
            ShareType shareType,
            LocalDate rentalStartDate,
            LocalDate rentalEndDate,
            ArticleStatus status,
            UserInfo writer,
            List<String> imageUrls,
            Boolean liked
    ) {
        public static Detail of(Article article, Boolean liked) {
            return new Detail(
                    article.getId(),
                    article.getTitle(),
                    article.getContent(),
                    article.getCreatedAt(),
                    article.getItemCategory(),
                    article.getShareType(),
                    article.getRentalStartDate(),
                    article.getRentalEndDate(),
                    article.getStatus(),
                    UserInfo.from(article.getWriter()),
                    article.getImages(),
                    liked
            );
        }
    }

    public record Simple(
            Long articleId,
            String title,
            String imageUrl,
            ArticleStatus status,
            UserInfo writer
    ) {
        public static Simple from(Article article) {
            return new Simple(
                    article.getId(),
                    article.getTitle(),
                    article.getFirstImageUrl(),
                    article.getStatus(),
                    UserInfo.from(article.getWriter())
            );
        }
    }
}
