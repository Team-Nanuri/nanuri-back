package team.hackerping.nanuri.chat.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import team.hackerping.nanuri.user.domain.User;
import team.hackerping.nanuri.article.domain.Article;

import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class ChatRoom {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CHAT_ROOM_ID")
    private Long id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "ARTICLE_ID")
    private Article article;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "RECIPIENT_ID")
    private User recipient;

    private LocalDateTime createdAt;
}
