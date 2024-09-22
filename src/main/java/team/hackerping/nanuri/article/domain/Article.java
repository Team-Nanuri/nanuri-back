package team.hackerping.nanuri.article.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.SoftDelete;
import team.hackerping.nanuri.user.domain.User;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@SoftDelete(columnName = "DELETED")
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE) @Builder(access = AccessLevel.PRIVATE)
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

    @Column(name = "IMAGE_URLS")
    @Convert(converter = ArticleImage.ArticleImageConverter.class)
    private ArticleImage image;

    @Builder.Default
    @NotNull
    @Enumerated(EnumType.STRING)
    private ArticleStatus status = ArticleStatus.ONGOING;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "WRITER_ID")
    private User writer;

    public String getFirstImageUrl() {
        if (image == null)
            return null;

        return image.getUrls().get(0);
    }

    public List<String> getImages() {
        if (image == null)
            return null;

        return image.getUrls();
    }

    public void update(String title, String content, ItemCategory itemCategory, ShareType shareType, LocalDate rentalStartDate, LocalDate rentalEndDate, ArticleImage image) {
        this.title = title;
        this.content = content;
        this.itemCategory = itemCategory;
        this.shareType = shareType;
        this.rentalStartDate = rentalStartDate;
        this.rentalEndDate = rentalEndDate;
        this.image = image;
    }

    public void changeStatus(ArticleStatus status) {
        this.status = status;
    }

    public static Article of(String title, String content, ItemCategory itemCategory, ShareType shareType, LocalDate rentalStartDate, LocalDate rentalEndDate, ArticleImage image, User writer) {
        return Article.builder()
                .title(title)
                .content(content)
                .itemCategory(itemCategory)
                .shareType(shareType)
                .rentalStartDate(rentalStartDate)
                .rentalEndDate(rentalEndDate)
                .image(image)
                .writer(writer)
                .createdAt(LocalDateTime.now())
                .build();
    }
}
