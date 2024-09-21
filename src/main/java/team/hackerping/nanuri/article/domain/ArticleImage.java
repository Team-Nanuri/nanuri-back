package team.hackerping.nanuri.article.domain;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import team.hackerping.nanuri.global.application.JsonConverter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ArticleImage {
    private List<String> urls;

    public static ArticleImage of(List<String> urls) {
        return new ArticleImage(urls);
    }

    public static class ArticleImageConverter extends JsonConverter<ArticleImage> {}
}
