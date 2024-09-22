package team.hackerping.nanuri.article.presentation.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import team.hackerping.nanuri.article.application.info.ArticleInfo;
import team.hackerping.nanuri.article.domain.ArticleStatus;
import team.hackerping.nanuri.article.domain.ItemCategory;
import team.hackerping.nanuri.article.domain.ShareType;
import team.hackerping.nanuri.user.presentation.dto.UserResponse;

public class ArticleResponse {
    public record Paging(
            List<Basic> contents,
            Integer totalPages
    ) {
        public static Paging of(ArticleInfo.Paging paging) {

            return new Paging(paging.articles().stream()
                    .map(Basic::from)
                    .toList(), paging.totalPage());
        }
    }

    public record Basic(
            Long articleId,
            String title,
            String content,
            String imageUrl,
            ArticleStatus status,
            LocalDateTime createdAt,
            ShareType shareType,
            Boolean liked
    ) {
        public static Basic from(ArticleInfo.Basic basic) {
            return new Basic(
                    basic.articleId(),
                    basic.title(),
                    basic.content(),
                    basic.imageUrl(),
                    basic.status(),
                    basic.createdAt(),
                    basic.shareType(),
                    basic.liked()
            );
        }
    }

    public record Detail(
            Long articleId,
            ArticleStatus status,
            String title,
            String content,
            List<String> imageUrls,
            ItemCategory category,
            ShareType shareType,
            LocalDate rentalStartDate,
            LocalDate rentalEndDate,
            LocalDateTime createdAt,
            Boolean liked,
            UserResponse.MaskedUserDto writer
    ) {
        public static Detail from(ArticleInfo.Detail detail) {
            return new Detail(
                    detail.id(),
                    detail.status(),
                    detail.title(),
                    detail.content(),
                    detail.imageUrls(),
                    detail.itemCategory(),
                    detail.shareType(),
                    detail.rentalStartDate(),
                    detail.rentalEndDate(),
                    detail.createdAt(),
                    detail.liked(),
                    UserResponse.MaskedUserDto.from(detail.writer())
            );
        }
    }

    public record Simple(
            Long articleId,
            ArticleStatus status,
            String title,
            String imageUrl,
            UserResponse.MaskedUserDto writer
    ) {
        public static Simple from(ArticleInfo.Simple simple) {
            return new Simple(
                    simple.articleId(),
                    simple.status(),
                    simple.title(),
                    simple.imageUrl(),
                    UserResponse.MaskedUserDto.from(simple.writer())
            );
        }
    }
}
