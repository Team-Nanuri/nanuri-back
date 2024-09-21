package team.hackerping.nanuri.global.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResponse> runtimeExceptionHandler(RuntimeException exception) {
        log.error("RuntimeException: ", exception);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ErrorResponse.from(exception));
    }

    @ExceptionHandler(NanuriException.class)
    public ResponseEntity<ErrorResponse> nanuriExceptionHandler(NanuriException exception) {
        log.error("NanuriException: ", exception.getErrorCode().getMessage(), exception.getDetail());
        return ResponseEntity.status(exception.getErrorCode().getHttpStatus()).body(ErrorResponse.from(exception));
    }
}
