package org.jacobo.adyd.config;

import jakarta.validation.ConstraintViolationException;
import jakarta.validation.ValidationException;
import lombok.extern.slf4j.Slf4j;
import org.jacobo.adyd.api.dto.InternalServerErrorDto;
import org.jacobo.adyd.api.dto.NotFoundDto;
import org.jacobo.adyd.domain.exception.AdydException;
import org.jacobo.adyd.domain.exception.ConflictRunTimeException;
import org.jacobo.adyd.domain.exception.NotFoundRunTimeException;
import org.springframework.data.core.PropertyReferenceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundRunTimeException.class)
    public ResponseEntity<NotFoundDto> handleNotFoundRunTimeException(NotFoundRunTimeException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new NotFoundDto().message(e.getMessage()));
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<NotFoundDto> handleConstraintViolationException(ConstraintViolationException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new NotFoundDto().message(e.getMessage()));
    }

    @ExceptionHandler(AdydException.class)
    public ResponseEntity<NotFoundDto> handleAdydException(AdydException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new NotFoundDto().message(e.getMessage()));
    }

    @ExceptionHandler(PropertyReferenceException.class)
    public ResponseEntity<NotFoundDto> handlePropertyReferenceException(PropertyReferenceException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new NotFoundDto().message(e.getMessage()));
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<?> handleValidationException(ValidationException e) {
        if (e.getCause() instanceof AdydException cause) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new NotFoundDto().message(cause.getMessage()));
        }
        log.error("Validation error", e);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new InternalServerErrorDto().message("Internal Server Error"));
    }

    @ExceptionHandler(ConflictRunTimeException.class)
    public ResponseEntity<NotFoundDto> handleConflictRunTimeException(ConflictRunTimeException e) {
        return ResponseEntity.status(e.getHttpStatus())
                .body(new NotFoundDto().message(e.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<InternalServerErrorDto> handleException(Exception e) {
        log.error("Unexpected error", e);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new InternalServerErrorDto().message("Internal Server Error"));
    }
}