package team.hackerping.nanuri.auth.client;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class OpenAiAuthClientTest {
    private final OpenAiAuthClient openAiAuthClient;

    @Autowired
    OpenAiAuthClientTest(OpenAiAuthClient openAiAuthClient) {
        this.openAiAuthClient = openAiAuthClient;
    }

    @Test
    @DisplayName("OpenAi URL 호출 테스트")
    void openAiCllTest() {
        final String profileImageUrl = "testFile";

        var response = openAiAuthClient.verifyProfile(profileImageUrl);
//        assertThat(response).isEqualTo(UserType.EXCHANGE);
    }
}