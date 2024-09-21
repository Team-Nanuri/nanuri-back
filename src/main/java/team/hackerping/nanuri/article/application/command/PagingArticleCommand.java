package team.hackerping.nanuri.article.application.command;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import org.springframework.data.domain.Pageable;
import team.hackerping.nanuri.article.domain.ArticleStatus;
import team.hackerping.nanuri.article.domain.ItemCategory;
import team.hackerping.nanuri.article.domain.ShareType;
import team.hackerping.nanuri.article.domain.Sort;
import team.hackerping.nanuri.global.application.SelfValidating;
import team.hackerping.nanuri.global.exception.NanuriException;
import team.hackerping.nanuri.global.exception.code.ArticleError;

import java.util.ArrayList;
import java.util.List;

@Getter
public class PagingArticleCommand extends SelfValidating<PagingArticleCommand> {
    @NotNull
    private final Long userId;
    @NotNull
    private final Pageable pageable;
    private final Long writerId;
    private final List<ItemCategory> categories;
    @Size(max = 20)
    private final String keyword;
    private final ShareType shareType;
    private final ArticleStatus status;
    private final Sort sort;

    public PagingArticleCommand(Long userId, Pageable pageable, Long writerId, String categories, String keyword, ShareType shareType, ArticleStatus status, Sort sort) {
        this.userId = userId;
        this.pageable = pageable;
        this.writerId = writerId;
        this.categories = convertStringToItemCategory(categories);
        this.keyword = keyword;
        this.shareType = shareType;
        this.status = status;
        this.sort = sort != null ? sort : Sort.CREATED_AT_DESC;
    }

    public List<ItemCategory> convertStringToItemCategory(String categories) {
        if (categories == null || categories.isEmpty())
            return null;

        StringBuilder builder = new StringBuilder();

        List<ItemCategory> result = new ArrayList<>();

        for (String category : categories.split(",")) {
            try {
                result.add(ItemCategory.valueOf(category.toUpperCase()));
            } catch (IllegalArgumentException e) {
                builder.append(category).append(", ");
            }
        }

        if (builder.isEmpty())
            return result;

        builder.deleteCharAt(builder.length() - 1);
        throw new NanuriException(ArticleError.ILLEGAL_CATEGORY_SEARCH, builder.toString());
    }
}
