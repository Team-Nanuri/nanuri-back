package team.hackerping.nanuri.infra.client;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "papago")
public record PapagoProperties(
        String apiUrl,
        String clientId,
        String clientSecret
) {
}