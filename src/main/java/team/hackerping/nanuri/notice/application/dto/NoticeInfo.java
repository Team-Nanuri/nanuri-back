package team.hackerping.nanuri.notice.application.dto;

import java.time.LocalDateTime;
import team.hackerping.nanuri.notice.domain.Notice;

public class NoticeInfo {

    public record Detail(
            String title,
            String content,
            LocalDateTime createdAt
    ) {
        public static Detail from(Notice notice) {
            return new Detail(notice.getTitle(), notice.getContent(), notice.getCreatedAt());
        }
    }
}
