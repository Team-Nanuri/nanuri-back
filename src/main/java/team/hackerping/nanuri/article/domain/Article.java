package team.hackerping.nanuri.article.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import team.hackerping.nanuri.user.domain.User;

import java.time.LocalDate;
import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Article {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ARTICLE_ID")
    private Long id;

    @NotNull
    private String title;

    @NotNull
    private String content;

    @NotNull
    private LocalDateTime createdAt;

    @NotNull
    @Enumerated(EnumType.STRING)
    private ItemCategory itemCategory;

    @NotNull
    @Enumerated(EnumType.STRING)
    private ShareType shareType;

    private LocalDate rentalStartDate;

    private LocalDate rentalEndDate;

    @NotNull
    @Enumerated(EnumType.STRING)
    private ArticleStatus status = ArticleStatus.ONGOING;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "WRITER_ID")
    private User writer;
}
