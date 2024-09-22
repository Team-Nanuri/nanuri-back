package team.hackerping.nanuri.article.presentation.dto;

import java.time.LocalDate;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;
import team.hackerping.nanuri.article.application.command.RegisterArticleCommand;
import team.hackerping.nanuri.article.application.command.UpdateArticleCommand;
import team.hackerping.nanuri.article.domain.ArticleStatus;
import team.hackerping.nanuri.article.domain.ItemCategory;
import team.hackerping.nanuri.article.domain.ShareType;

public class ArticleRequest {
    public record Upsert(
            ItemCategory category,
            ShareType shareType,
            LocalDate rentalStartDate,
            LocalDate rentalEndDate,
            String title,
            String content,
            List<MultipartFile> images
    ) {
        public RegisterArticleCommand toCommand(Long writerId) {
            return new RegisterArticleCommand(
                    writerId,
                    category,
                    shareType,
                    rentalStartDate,
                    rentalEndDate,
                    title,
                    content,
                    images
            );
        }

        public UpdateArticleCommand toCommand(Long userId, Long articleId) {
            return new UpdateArticleCommand(
                    articleId,
                    userId,
                    category,
                    shareType,
                    rentalStartDate,
                    rentalEndDate,
                    title,
                    content,
                    images
            );
        }
    }

    public record Status(
            ArticleStatus status
    ) { }
}