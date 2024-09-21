package team.hackerping.nanuri.article.presentation.dto;

import team.hackerping.nanuri.article.domain.ArticleStatus;
import team.hackerping.nanuri.article.domain.ShareType;
import team.hackerping.nanuri.article.domain.Sort;

public record ArticlePagingParams(
        Long writerId,
        String categories,
        String keyword,
        ShareType shareType,
        ArticleStatus status,
        Sort sort
) {
}
