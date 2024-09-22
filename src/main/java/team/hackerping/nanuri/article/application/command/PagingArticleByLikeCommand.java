package team.hackerping.nanuri.article.application.command;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import org.springframework.data.domain.Pageable;
import team.hackerping.nanuri.global.application.SelfValidating;

@Getter
public class PagingArticleByLikeCommand extends SelfValidating<PagingArticleByLikeCommand> {

    @NotNull
    private final Long userId;
    @NotNull
    private final Pageable pageable;

    public PagingArticleByLikeCommand(Long userId, Pageable pageable) {
        this.userId = userId;
        this.pageable = pageable;
        this.validateSelf();
    }
}
