package team.hackerping.nanuri.global.exception.code;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum WebError implements ErrorCode {

    NOT_READABLE_HTTP_MESSAGE(HttpStatus.BAD_REQUEST, "W000","HTTP 메시지를 읽는 중 알 수 없는 오류가 발생하였습니다."),
    ILLEGAL_JSON(HttpStatus.BAD_REQUEST, "W001","올바른 JSON 형식이 아닙니다."),

    SERVLET_ERROR(HttpStatus.BAD_REQUEST, "W000","HTTP 메시지를 서블릿에 매핑할 수 없습니다."),
    MISSING_REQUEST_PARAMETER(HttpStatus.BAD_REQUEST, "W101","필수 요청 파라미터가 누락되었습니다."),
    MISSING_PATH_VARIABLE(HttpStatus.BAD_REQUEST, "W102","필수 경로 변수가 누락되었습니다."),
    MISSING_REQUEST_HEADER(HttpStatus.BAD_REQUEST, "W103","필수 요청 헤더가 누락되었습니다."),
    MISSING_REQUEST_COOKIE(HttpStatus.BAD_REQUEST, "W104","필수 요청 쿠키가 누락되었습니다."),
    MISSING_MATRIX_VARIABLE(HttpStatus.BAD_REQUEST, "W105","필수 매트릭스 변수가 누락되었습니다.");

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;

    WebError(HttpStatus httpStatus, String code, String message) {
        this.httpStatus = httpStatus;
        this.code = code;
        this.message = message;
    }
}
