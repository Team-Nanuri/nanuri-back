package team.hackerping.nanuri.article.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import team.hackerping.nanuri.user.domain.User;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "ARTICLE_LIKE")
public class Like {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "LIKE_ID")
    private Long id;

    @NotNull
    @ManyToOne(optional = false)
    @JoinColumn(name = "USER_ID")
    private User user;

    @NotNull
    @ManyToOne(optional = false)
    @JoinColumn(name = "ARTICLE_ID")
    private Article article;

    private LocalDateTime createdAt;
}
