package team.hackerping.nanuri.article.application.info;

import team.hackerping.nanuri.article.domain.Article;
import team.hackerping.nanuri.article.domain.ArticleStatus;
import team.hackerping.nanuri.article.domain.ItemCategory;
import team.hackerping.nanuri.article.domain.ShareType;
import team.hackerping.nanuri.user.application.UserInfo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class ArticleInfo {

    public record Basic(
            Long articleId,
            String title,
            String content,
            ArticleStatus status,
            LocalDateTime createdAt,
            ShareType shareType,
            String imageUrl
    ) {
        public static Basic from(Article article){
            return new Basic(
                    article.getId(),
                    article.getTitle(),
                    article.getContent(),
                    article.getStatus(),
                    article.getCreatedAt(),
                    article.getShareType(),
                    article.getFirstImageUrl()
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
        public static Detail of(Article article, Boolean liked){
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
}
