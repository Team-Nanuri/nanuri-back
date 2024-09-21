package team.hackerping.nanuri.notice.presentation;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import team.hackerping.nanuri.notice.application.NoticeService;
import team.hackerping.nanuri.notice.presentation.dto.NoticeResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/notices")
public class NoticeRestController implements NoticeController {
    private final NoticeService noticeService;

    @Override
    @GetMapping
    public ResponseEntity<List<NoticeResponse.Info>> getNotices(Authentication authentication) {
        var userId = Long.parseLong(authentication.getName());
        var notices = noticeService.getNotices(userId);

        var response = notices.stream()
                .map(NoticeResponse.Info::from)
                .toList();
        return ResponseEntity.ok()
                .body(response);
    }
}
