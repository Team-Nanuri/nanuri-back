package team.hackerping.nanuri.translation.client;

import com.fasterxml.jackson.databind.JsonNode;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import team.hackerping.nanuri.global.exception.NanuriException;
import team.hackerping.nanuri.global.exception.code.ClientError;
import team.hackerping.nanuri.infra.client.PapagoProperties;

@Component
@Slf4j
@RequiredArgsConstructor
public class PapagoClient {
    private final PapagoProperties papagoProperties;
    private final RestTemplate restTemplate;


    @PreAuthorize("hasRole('ACTIVATED')")
    public String translate(String text) {
        var url = papagoProperties.apiUrl();
        var header = this.getHeaders();
        var request = this.getFormData(text);

        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(request, header);
        ResponseEntity<JsonNode> response;
        try {
            response = restTemplate.postForEntity(url, requestEntity, JsonNode.class);
        } catch (Exception e) {
            log.error("Error occurred while calling Papago API", e);
            throw new NanuriException(ClientError.PAPAGO_CLIENT_ERROR);
        }
        if (this.isErrorResponse(response)) {
            throw new NanuriException(ClientError.PAPAGO_CLIENT_ERROR);
        }
        return this.parseResponse(response.getBody());
    }

    private boolean isErrorResponse(ResponseEntity<JsonNode> response) {
        return response.getStatusCode().isError() || Objects.isNull(response.getBody());
    }

    private HttpHeaders getHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-NCP-APIGW-API-KEY-ID", papagoProperties.clientId());
        headers.add("X-NCP-APIGW-API-KEY", papagoProperties.clientSecret());
        headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_FORM_URLENCODED_VALUE);
        return headers;
    }

    private MultiValueMap<String, String> getFormData(String text) {
        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
        formData.add("source", "auto");
        formData.add("target", "ko");
        formData.add("text", text);

        return formData;
    }

    private String parseResponse(JsonNode response) {
        return response.get("message").get("result").get("translatedText").asText();
    }
}
