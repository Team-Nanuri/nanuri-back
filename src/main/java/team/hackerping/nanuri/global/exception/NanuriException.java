package team.hackerping.nanuri.global.exception;

import lombok.Getter;
import team.hackerping.nanuri.global.exception.code.ErrorCode;

@Getter
public class NanuriException extends RuntimeException {

    private final ErrorCode errorCode;
    private final String detail;

    public NanuriException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
        detail = null;
    }

    public NanuriException(ErrorCode errorCode, String detail) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
        this.detail = detail;
    }
}
