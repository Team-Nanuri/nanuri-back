package team.hackerping.nanuri.global.exception.code;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ArticleError implements ErrorCode {

    ILLEGAL_DONATION(HttpStatus.BAD_REQUEST, "A003","DONATION 타입은 대여 시작 일자와 종료 일자가 필요하지 않습니다."),
    ILLEGAL_RENTAL(HttpStatus.BAD_REQUEST, "A004","RENTAL 타입은 대여 시작 일자와 종료 일자가 필요합니다."),
    ILLEGAL_DATE(HttpStatus.BAD_REQUEST, "A005","대여 종료일자가 대여 시작일자보다 빠를 수 없습니다."),
    ILLEGAL_CATEGORY_SEARCH(HttpStatus.BAD_REQUEST, "A006","유효하지 않은 카테고리 값으로 검색을 시도했습니다."),

    ALREADY_LIKED(HttpStatus.BAD_REQUEST, "A200", "이미 좋아요 한 게시글입니다."),
    CANNOT_LIKE_MY_ARTICLE(HttpStatus.BAD_REQUEST, "A201", "자신의 게시글에는 좋아요 할 수 없습니다."),

    FORBIDDEN(HttpStatus.FORBIDDEN, "A300", "게시글에 권한이 없습니다."),
    UPDATE_FORBIDDEN(HttpStatus.FORBIDDEN, "A301", "게시글 수정 권한이 없습니다.");

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;

    ArticleError(HttpStatus httpStatus, String code, String message) {
        this.httpStatus = httpStatus;
        this.code = code;
        this.message = message;
    }
}
