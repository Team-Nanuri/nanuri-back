package team.hackerping.nanuri.infra.s3;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "aws")
public record AwsProperties(
        String accessKey,
        String secretKey,
        String region
) {
}