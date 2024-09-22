package team.hackerping.nanuri.global.exception.code;

import org.springframework.http.HttpStatus;

public enum ChatError implements ErrorCode {
    NOT_PARTICIPANT(HttpStatus.FORBIDDEN, "C001", "채팅방 참여자가 아닙니다.");

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;

    ChatError(HttpStatus httpStatus, String code, String message) {
        this.httpStatus = httpStatus;
        this.code = code;
        this.message = message;
    }

    @Override
    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
