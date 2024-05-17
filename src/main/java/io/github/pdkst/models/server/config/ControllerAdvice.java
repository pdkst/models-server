package io.github.pdkst.models.server.config;

import io.github.pdkst.models.server.common.ErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author pdkst
 * @since 2024/01/01
 */
@Slf4j
@RestControllerAdvice
public class ControllerAdvice {
    @ExceptionHandler(Exception.class)
    public ErrorResponse exceptionHandler(Exception e, HttpServletRequest request) {
        log.error("exceptionHandler => handle exception; url={}", request.getRequestURI(), e);
        return ErrorResponse.of("server_error", e.getMessage());
    }
}
