package team.hackerping.nanuri.auth.persistence;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;
import team.hackerping.nanuri.infra.s3.BucketProperties;

@Repository
@RequiredArgsConstructor
public class ProfileS3Repository {
    private static final Logger log = LoggerFactory.getLogger(ProfileS3Repository.class);
    private final AmazonS3Client amazonS3Client;
    private final BucketProperties bucketProperties;

    public String uploadProfileImage(String username, MultipartFile profileImage) {
        var bucketName = bucketProperties.bucketName();
        var key = bucketProperties.profileImagePrefix() + username;
        log.info("프로필 이미지 업로드 시작: bucketName={}, key={}", bucketName, key);
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(profileImage.getSize());  // 파일 크기 설정
        metadata.setContentType(profileImage.getContentType());  // 파일 타입 설정 (예: image/jpeg)

        try {
            amazonS3Client.putObject(bucketName, key, profileImage.getInputStream(), metadata);
        } catch (Exception e) {
            log.error("프로필 이미지 업로드 실패", e);
            return null;
        }
        var url = amazonS3Client.getUrl(bucketProperties.bucketName(), key);
        log.info("프로필 이미지 업로드 성공: url={}", url);
        return url.toString();
    }
}
