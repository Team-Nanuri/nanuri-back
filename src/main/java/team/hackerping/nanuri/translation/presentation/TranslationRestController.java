package team.hackerping.nanuri.translation.presentation;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import team.hackerping.nanuri.translation.client.PapagoClient;
import team.hackerping.nanuri.translation.presentation.dto.TranslationResponse;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/translation")
public class TranslationRestController implements TranslationController {
    private final PapagoClient papagoClient;

    @Override
    @GetMapping()
    public ResponseEntity<TranslationResponse> translate(@RequestParam String text) {
        log.info("Request to translate text: {}", text);
        var translated = papagoClient.translate(text);

        var response = new TranslationResponse(text, translated);
        return ResponseEntity.ok()
                .body(response);
    }
}