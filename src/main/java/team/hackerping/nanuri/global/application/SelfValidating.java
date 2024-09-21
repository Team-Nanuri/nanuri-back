package team.hackerping.nanuri.global.application;

import jakarta.validation.*;
import lombok.extern.slf4j.Slf4j;
import team.hackerping.nanuri.global.exception.NanuriException;
import team.hackerping.nanuri.global.exception.code.GeneralError;

import java.util.Set;

@Slf4j
public abstract class SelfValidating<T> {

    private final Validator validator;

    public SelfValidating() {
        try (ValidatorFactory factory = Validation.buildDefaultValidatorFactory()) {
            validator = factory.getValidator();
        }
    }

    protected void validateSelf() {
        Set<ConstraintViolation<T>> violations = validator.validate((T)this);

        if (!violations.isEmpty()) {
            String path = violations
                    .stream()
                    .map(violation -> violation.getPropertyPath() + ": " + violation.getMessage())
                    .reduce((s1, s2) -> s1 + ", " + s2)
                    .orElse("");

            throw new NanuriException(GeneralError.CONSTRAINT_VIOLATION, path);
        }
    }
}