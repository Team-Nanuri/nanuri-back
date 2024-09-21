package team.hackerping.nanuri.notice.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Notice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    private String title;

    private String content;

    private Notice(Long userId, String title, String content) {
        this.userId = userId;
        this.title = title;
        this.content = content;
    }

    public static Notice of(Long userId, String title, String content) {
        return new Notice(userId, title, content);
    }
}
