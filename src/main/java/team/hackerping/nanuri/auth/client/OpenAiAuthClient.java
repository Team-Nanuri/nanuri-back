package team.hackerping.nanuri.auth.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.View;
import team.hackerping.nanuri.infra.client.OpenAiProperties;
import team.hackerping.nanuri.user.domain.UserType;

@Slf4j
@Component
@RequiredArgsConstructor
public class OpenAiAuthClient {
    private final OpenAiProperties openAiProperties;
    private final RestTemplate restClient;
    private final ObjectMapper objectMapper;
    private final View error;

    public UserType verifyProfile(String profileImageUrl) {
        String body = null;
        try {
            body = makeBody(profileImageUrl);
        } catch (Exception e) {
            log.error("Error", e);
        }
        log.info(body);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + openAiProperties.secretKey());

        HttpEntity<String> entity = new HttpEntity<>(body, headers);

        var response = restClient.postForEntity(openAiProperties.url(), entity, JsonNode.class);
        if (response.getStatusCode().isError() || Objects.isNull(response.getBody())) {
            log.info("OpenAI API 호출 실패");
            return null;
        }

        var authResult = extractAuthResult(response.getBody());
        log.info("OpenAI API 호출 성공: {}", authResult);

        return UserType.valueOf(authResult);
    }

    private String makeBody(String profileImageUrl) throws JsonProcessingException {
        var imgMessage = makeProfileMessage(profileImageUrl);

        Map<String, Object> body = new HashMap<>();
        body.put("model", openAiProperties.model());
        body.put("messages", List.of(imgMessage).toArray());

        return objectMapper.writeValueAsString(body);
    }


    private Map<String, Object> makeProfileMessage(String profileImageUrl) {
        Map<String, Object> imageUrlContent = new HashMap<>();
        Map<String, Object> systemMessage = new HashMap<>();

        systemMessage.put("type", "text");
        systemMessage.put("text", openAiProperties.systemMessage());

        imageUrlContent.put("type", "image_url");
        imageUrlContent.put("image_url", Map.of("url", profileImageUrl));

        Map<String, Object> imgMessage = new HashMap<>();
        imgMessage.put("role", "user");
        imgMessage.put("content", List.of(systemMessage, imageUrlContent).toArray());

        return imgMessage;
    }

    public String extractAuthResult(JsonNode response) {
        return response.path("choices").get(0).path("message").path("content").asText();
    }
}
