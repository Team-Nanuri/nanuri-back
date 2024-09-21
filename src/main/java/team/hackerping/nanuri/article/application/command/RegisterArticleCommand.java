package team.hackerping.nanuri.article.application.command;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;
import team.hackerping.nanuri.article.domain.ItemCategory;
import team.hackerping.nanuri.article.domain.ShareType;
import team.hackerping.nanuri.global.application.SelfValidating;
import team.hackerping.nanuri.global.exception.NanuriException;
import team.hackerping.nanuri.global.exception.code.ArticleError;

import java.time.LocalDate;
import java.util.List;

@Getter
public class RegisterArticleCommand extends SelfValidating<RegisterArticleCommand> {
    @NotNull
    private final Long writerId;
    @NotNull
    private final ItemCategory category;
    @NotNull
    private final ShareType shareType;
    private final LocalDate rentalStartDate;
    private final LocalDate rentalEndDate;
    @NotBlank @Size(max = 20)
    private final String title;
    @NotBlank @Size(max = 2000)
    private final String content;
    @Size(max = 5)
    private final List<MultipartFile> images;

    public RegisterArticleCommand(Long writerId, ItemCategory category, ShareType shareType, LocalDate rentalStartDate, LocalDate rentalEndDate, String title, String content, List<MultipartFile> images) {
        this.writerId = writerId;
        this.category = category;
        this.shareType = shareType;
        this.rentalStartDate = rentalStartDate;
        this.rentalEndDate = rentalEndDate;

        validateShareType();

        this.title = title;
        this.content = content;
        this.images = images;
        this.validateSelf();
    }

    private void validateShareType() {
        if (shareType == ShareType.RENTAL) {
            if (rentalStartDate == null || rentalEndDate == null) {
                throw new NanuriException(ArticleError.ILLEGAL_RENTAL);
            }
            validateRentalDate();
        }
        else {
            if (rentalStartDate != null || rentalEndDate != null) {
                throw new NanuriException(ArticleError.ILLEGAL_DONATION);
            }
        }
    }

    private void validateRentalDate() {
        if (rentalStartDate.isAfter(rentalEndDate)) {
            throw new NanuriException(ArticleError.ILLEGAL_DATE);
        }
    }
}
