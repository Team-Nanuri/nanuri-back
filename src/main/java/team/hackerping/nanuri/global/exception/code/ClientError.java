package team.hackerping.nanuri.global.exception.code;

import org.springframework.http.HttpStatus;

public enum ClientError implements ErrorCode {
    PAPAGO_CLIENT_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "P001", "파파고 클라이언트 오류입니다.");

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;

    ClientError(HttpStatus httpStatus, String code, String message) {
        this.httpStatus = httpStatus;
        this.code = code;
        this.message = message;
    }

    @Override
    public HttpStatus getHttpStatus() {
        return null;
    }

    @Override
    public String getCode() {
        return "";
    }

    @Override
    public String getMessage() {
        return "";
    }
}
