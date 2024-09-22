package team.hackerping.nanuri.article.domain;

import lombok.Getter;

@Getter
public enum Sort {
    CREATED_AT_DESC("createdAt", "DESC"),
    CREATED_AT_ASC("createdAt", "ASC"),
    RENTAL_START_DATE_DESC("rentalStartDate", "DESC"),
    RENTAL_START_DATE_ASC("rentalStartDate", "ASC");

    private final String field;
    private final String direction;

    Sort(String field, String direction) {
        this.field = field;
        this.direction = direction;
    }
}
