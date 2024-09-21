package team.hackerping.nanuri.global.exception;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.*;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import team.hackerping.nanuri.global.exception.code.WebError;

import java.util.List;

@Slf4j
@RestControllerAdvice
public class WebExceptionHandler {

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponse> handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        NanuriException ex;

        var cause = e.getCause();

        if (cause instanceof JsonParseException)
            ex = new NanuriException(WebError.ILLEGAL_JSON);

        if (cause instanceof JsonMappingException) {
            var path = buildPath((JsonMappingException) cause);

            if (cause instanceof InvalidFormatException) {
                Class<?> targetType = ((InvalidFormatException) cause).getTargetType();

                if (targetType.isEnum())
                    ex = new NanuriException(WebError.ILLEGAL_JSON, buildEnumMessage(targetType, path));
                else
                    ex = new NanuriException(WebError.ILLEGAL_JSON, path + "은(는) " + targetType.getSimpleName() + "이어야 합니다.");
            }

            if (cause instanceof MismatchedInputException) {
                Class<?> targetType = ((MismatchedInputException) cause).getTargetType();

                if (targetType != null)
                    ex = new NanuriException(WebError.ILLEGAL_JSON, path + "을(를) " + targetType.getSimpleName() + "에 매핑할 수 없습니다.");
                else
                    ex = new NanuriException(WebError.ILLEGAL_JSON, path + "을(를) 매핑하는 데 실패하였습니다.");
            }

            ex = new NanuriException(WebError.ILLEGAL_JSON, "JSON 매핑에 실패하였습니다.");
        }

        ex = new NanuriException(WebError.NOT_READABLE_HTTP_MESSAGE);

        return ResponseEntity.status(ex.getErrorCode().getHttpStatus()).body(ErrorResponse.from(ex));
    }

    private String buildPath(JsonMappingException e) {
        List<JsonMappingException.Reference> references = e.getPath();

        StringBuilder pathBuilder = new StringBuilder();
        for (JsonMappingException.Reference reference : references) {
            if (!pathBuilder.isEmpty())
                pathBuilder.append("/");
            pathBuilder.append(reference.getFieldName());
        }

        return pathBuilder.toString();
    }

    private String buildEnumMessage(Class<?> targetType, String path) {
        StringBuilder builder = new StringBuilder();

        builder.append(path).append("은(는) ");

        Object[] enumConstants = targetType.getEnumConstants();
        for (Object enumConstant : enumConstants)
            builder.append(enumConstant.toString()).append(", ");

        if (!builder.isEmpty())
            builder.setLength(builder.length() - 2);

        builder.append(path).append(" 중 하나여야 합니다.");

        return builder.toString();
    }

    @ExceptionHandler(ServletRequestBindingException.class)
    public ResponseEntity<ErrorResponse> handleServletRequestBindingException(ServletRequestBindingException e) {
        NanuriException ex;

        if (e instanceof MissingServletRequestParameterException)
            ex = new NanuriException(WebError.MISSING_REQUEST_PARAMETER, ((MissingServletRequestParameterException) e).getParameterName());

        if (e instanceof MissingPathVariableException)
            ex = new NanuriException(WebError.MISSING_PATH_VARIABLE, ((MissingPathVariableException) e).getVariableName());

        if (e instanceof MissingRequestHeaderException)
            ex = new NanuriException(WebError.MISSING_REQUEST_HEADER, ((MissingRequestHeaderException) e).getHeaderName());

        if (e instanceof MissingRequestCookieException)
            ex = new NanuriException(WebError.MISSING_REQUEST_COOKIE, ((MissingRequestCookieException) e).getCookieName());

        if (e instanceof MissingMatrixVariableException)
            ex = new NanuriException(WebError.MISSING_MATRIX_VARIABLE, ((MissingMatrixVariableException) e).getVariableName());

        ex = new NanuriException(WebError.SERVLET_ERROR);

        return ResponseEntity.status(ex.getErrorCode().getHttpStatus()).body(ErrorResponse.from(ex));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        FieldError fieldError = e.getBindingResult().getFieldError();

        NanuriException ex = new NanuriException(WebError.INVALID_ARGUMENT, fieldError.getField());

        return ResponseEntity.status(ex.getErrorCode().getHttpStatus()).body(ErrorResponse.from(ex));
    }
}
