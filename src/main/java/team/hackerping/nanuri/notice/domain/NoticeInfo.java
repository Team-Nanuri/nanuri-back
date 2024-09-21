package team.hackerping.nanuri.notice.domain;

import lombok.Getter;

@Getter
public enum NoticeInfo {
    AUTH_STARTED("[인증]", "인증이 시작되었습니다."),
    AUTH_SUCCESS("[인증]", "인증이 성공적으로 완료되었습니다."),
    AUTH_FAIL("[인증]", "인증이 실패하였습니다.");

    private final String title;
    private final String content;

    NoticeInfo(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
