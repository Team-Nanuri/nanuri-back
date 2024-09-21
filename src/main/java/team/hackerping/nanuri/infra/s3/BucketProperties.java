package team.hackerping.nanuri.infra.s3;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "s3")
public record BucketProperties(
        String bucketName,
        String profileImagePrefix,
        String articleImagePrefix
) {
}
