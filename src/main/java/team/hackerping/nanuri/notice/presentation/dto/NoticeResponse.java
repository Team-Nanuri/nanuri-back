package team.hackerping.nanuri.notice.presentation.dto;

import team.hackerping.nanuri.notice.application.dto.NoticeInfo;

public class NoticeResponse {

    public record Info(
            String title,
            String content
    ) {
        public static Info from(NoticeInfo.Detail detail) {
            return new Info(detail.title(), detail.content());
        }
    }
}
