package team.hackerping.nanuri.global.exception.code;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ArticleError implements ErrorCode {

    ILLEGAL_DONATION(HttpStatus.BAD_REQUEST, "A003","DONATION 타입은 대여 시작 일자와 종료 일자가 필요하지 않습니다."),
    ILLEGAL_RENTAL(HttpStatus.BAD_REQUEST, "A004","RENTAL 타입은 대여 시작 일자와 종료 일자가 필요합니다."),
    ILLEGAL_DATE(HttpStatus.BAD_REQUEST, "A005","대여 종료일자가 대여 시작일자보다 빠를 수 없습니다."),
    ILLEGAL_CATEGORY_SEARCH(HttpStatus.BAD_REQUEST, "A006","유효하지 않은 카테고리 값으로 검색을 시도했습니다.");

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;

    ArticleError(HttpStatus httpStatus, String code, String message) {
        this.httpStatus = httpStatus;
        this.code = code;
        this.message = message;
    }
}
