package team.hackerping.nanuri.notice.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import team.hackerping.nanuri.notice.domain.Notice;
import team.hackerping.nanuri.notice.domain.NoticeInfo;
import team.hackerping.nanuri.notice.persistence.NoticeRepository;

@Service
@RequiredArgsConstructor
public class NoticeService {
    private final NoticeRepository noticeRepository;

    public void sendAuthStartNotice(Long userId) {
        var noticeInfo = NoticeInfo.AUTH_STARTED;
        var notice = Notice.of(userId, noticeInfo.getTitle(), noticeInfo.getContent());
        noticeRepository.save(notice);
    }

    public void sendAuthNotice(Long userId, boolean isSuccess) {
        if (isSuccess) {
            registerAuthSuccessNotice(userId);
            return;
        }
        registerAuthFailedNotice(userId);
    }

    private void registerAuthSuccessNotice(Long userId) {
        var noticeInfo = NoticeInfo.AUTH_SUCCESS;
        var notice = Notice.of(userId, noticeInfo.getTitle(), noticeInfo.getContent());
        noticeRepository.save(notice);
    }

    private void registerAuthFailedNotice(Long userId) {
        var noticeInfo = NoticeInfo.AUTH_FAIL;
        var notice = Notice.of(userId, noticeInfo.getTitle(), noticeInfo.getContent());
        noticeRepository.save(notice);
    }
}
