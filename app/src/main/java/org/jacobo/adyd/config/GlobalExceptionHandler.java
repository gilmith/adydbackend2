package org.jacobo.adyd.config;

import lombok.extern.slf4j.Slf4j;
import org.jacobo.adyd.api.dto.InternalServerErrorDto;
import org.jacobo.adyd.api.dto.NotFoundDto;
import org.jacobo.adyd.domain.exception.NotFoundRunTimeException;
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

    @ExceptionHandler(Exception.class)
    public ResponseEntity<InternalServerErrorDto> handleException(Exception e) {
        log.error("Unexpected error", e);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new InternalServerErrorDto().message("Internal Server Error"));
    }
}