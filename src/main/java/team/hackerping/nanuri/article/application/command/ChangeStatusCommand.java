package team.hackerping.nanuri.article.application.command;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import team.hackerping.nanuri.article.domain.ArticleStatus;
import team.hackerping.nanuri.global.application.SelfValidating;


@Getter
public class ChangeStatusCommand extends SelfValidating<ChangeStatusCommand> {
    @NotNull
    private final Long userId;
    @NotNull
    private final Long articleId;
    @NotNull
    private final ArticleStatus status;

    public ChangeStatusCommand(Long userId, Long articleId, ArticleStatus status) {
        this.userId = userId;
        this.articleId = articleId;
        this.status = status;

        this.validateSelf();
    }
}
