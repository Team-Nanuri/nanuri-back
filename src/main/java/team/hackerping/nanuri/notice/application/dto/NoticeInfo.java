package team.hackerping.nanuri.notice.application.dto;

import team.hackerping.nanuri.notice.domain.Notice;

public class NoticeInfo {

    public record Detail(
            String title,
            String content
    ) {
        public static Detail from(Notice notice) {
            return new Detail(notice.getTitle(), notice.getContent());
        }
    }
}
