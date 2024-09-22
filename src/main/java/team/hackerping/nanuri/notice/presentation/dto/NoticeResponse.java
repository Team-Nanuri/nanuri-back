package team.hackerping.nanuri.notice.presentation.dto;

import java.time.LocalDateTime;
import team.hackerping.nanuri.notice.application.dto.NoticeInfo;

public class NoticeResponse {

    public record Info(
            String title,
            String content,
            LocalDateTime createdAt
    ) {
        public static Info from(NoticeInfo.Detail detail) {
            return new Info(detail.title(), detail.content(), detail.createdAt());
        }
    }
}
