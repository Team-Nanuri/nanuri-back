package team.hackerping.nanuri.global.exception;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ErrorResponse {

    private final String code;
    private final String message;
    private final String detail;

    public static ErrorResponse from(RuntimeException e) {
        return new ErrorResponse(
                "E999",
                "서버 내부에서 알 수 없는 오류가 발생하였습니다.",
                null
        );
    }

    public static ErrorResponse from(NanuriException e) {
        return new ErrorResponse(
                e.getErrorCode().getCode(),
                e.getErrorCode().getMessage(),
                e.getDetail()
        );
    }
}
