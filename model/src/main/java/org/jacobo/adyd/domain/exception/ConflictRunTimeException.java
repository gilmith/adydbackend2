package org.jacobo.adyd.domain.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ConflictRunTimeException extends RuntimeException {

    private final HttpStatus httpStatus;

    public ConflictRunTimeException(String message) {
        super(message);
        httpStatus = HttpStatus.CONFLICT;
    }

}
