package team.hackerping.nanuri.article.persistence;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;
import team.hackerping.nanuri.auth.persistence.ProfileS3Repository;
import team.hackerping.nanuri.infra.s3.BucketProperties;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Repository
public class ArticleS3Repository {
    private static final Logger log = LoggerFactory.getLogger(ProfileS3Repository.class);
    private final AmazonS3Client amazonS3Client;
    private final BucketProperties bucketProperties;

    public List<String> uploadArticleImage(Long userId, List<MultipartFile> images) {
        if (images == null || images.isEmpty()) {
            return null;
        }

        var bucketName = bucketProperties.bucketName();

        List<String> imageUrls = new ArrayList<>();

        for (MultipartFile image : images) {
            var key = bucketProperties.articleImagePrefix() + userId.toString() + "/" + image.getOriginalFilename();
            log.info("프로필 이미지 업로드 시작: bucketName={}, key={}", bucketName, key);

            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentLength(image.getSize());  // 파일 크기 설정
            metadata.setContentType(image.getContentType());  // 파일 타입 설정 (예: image/jpeg)
            try {
                amazonS3Client.putObject(bucketName, key, image.getInputStream(), metadata);
            } catch (Exception e) {
                log.error("게시글 이미지 {} 업로드 실패", image.getOriginalFilename(), e);
                throw new RuntimeException();
            }
            var url = amazonS3Client.getUrl(bucketProperties.bucketName(), key);
            log.info("게시글 이미지 {} 업로드 성공: url={}", image.getOriginalFilename(), url);

            imageUrls.add(url.toString());
        }

        return imageUrls;
    }
}
