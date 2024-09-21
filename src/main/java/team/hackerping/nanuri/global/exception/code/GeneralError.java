package team.hackerping.nanuri.global.exception.code;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum GeneralError implements ErrorCode {

    NOT_FOUND(HttpStatus.NOT_FOUND, "G000","존재하지 않는 자원입니다."),
    CONSTRAINT_VIOLATION(HttpStatus.BAD_REQUEST, "G001","유효하지 않은 요청값이 들어왔습니다.");

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;

    GeneralError(HttpStatus httpStatus, String code, String message) {
        this.httpStatus = httpStatus;
        this.code = code;
        this.message = message;
    }
}
