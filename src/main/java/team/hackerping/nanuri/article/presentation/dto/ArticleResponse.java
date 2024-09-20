package team.hackerping.nanuri.article.presentation.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import team.hackerping.nanuri.article.domain.ArticleStatus;
import team.hackerping.nanuri.article.domain.ItemCategory;
import team.hackerping.nanuri.article.domain.ShareType;
import team.hackerping.nanuri.user.presentation.dto.UserResponse;

public class ArticleResponse {
    public record Paging(
            List<Info> contents,
            Integer totalPages
    ){}

    public record Info(
            Long articleId,
            String title,
            String content,
            String imageUrl,
            ArticleStatus status,
            LocalDateTime createdAt,
            ShareType shareType,
            Boolean liked
    ){}

    public record Detail(
            Long articleId,
            String title,
            String content,
            List<String> imageUrls,
            ItemCategory category,
            ShareType shareType,
            LocalDate rentalStartDate,
            LocalDate rentalEndDate,
            Boolean liked,
            UserResponse.MaskedUserInfo writer
    ){ }

    public record Simple(
            Long articleId,
            String title,
            String imageUrl,
            UserResponse.MaskedUserInfo writer
    ) { }
}
