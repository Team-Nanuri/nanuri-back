package team.hackerping.nanuri.notice.persistence;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import team.hackerping.nanuri.notice.domain.Notice;

public interface NoticeRepository extends JpaRepository<Notice, Long> {
    List<Notice> findByUserId(Long userId);
}
