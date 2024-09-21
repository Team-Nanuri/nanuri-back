package team.hackerping.nanuri.article.presentation.dto;

import team.hackerping.nanuri.article.domain.ArticleStatus;
import team.hackerping.nanuri.article.domain.ShareType;

public record ArticlePagingParams(
        Long writerId,
        String categories,
        String keyword,
        ShareType shareType,
        ArticleStatus status
) {

}
