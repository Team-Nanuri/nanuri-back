package team.hackerping.nanuri.infra.client;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "openai")
public record OpenAiProperties(
        String url,
        String secretKey,
        String model,
        String systemMessage
) {
}