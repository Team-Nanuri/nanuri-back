package team.hackerping.nanuri.global.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResponse> runtimeExceptionHandler(RuntimeException exception) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ErrorResponse.from(exception));
    }

    @ExceptionHandler(NanuriException.class)
    public ResponseEntity<ErrorResponse> nanuriExceptionHandler(NanuriException exception) {
        return ResponseEntity.status(exception.getErrorCode().getHttpStatus()).body(ErrorResponse.from(exception));
    }
}
