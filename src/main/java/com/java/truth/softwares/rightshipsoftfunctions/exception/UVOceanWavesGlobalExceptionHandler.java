package com.java.truth.softwares.rightshipsoftfunctions.exception;

import com.java.truth.softwares.rightshipsoftfunctions.exception.DuplicateEmailException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.ErrorResponseException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;
import java.util.List;

@RestControllerAdvice
public class UVOceanWavesGlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<UVOceanWavesApiError> handleNotFound(ResourceNotFoundException ex, HttpServletRequest req) {
        return build(HttpStatus.NOT_FOUND, "Not Found", ex.getMessage(), req.getRequestURI(), null);
    }

    @ExceptionHandler(DuplicateEmailException.class)
    public ResponseEntity<UVOceanWavesApiError> handleDuplicate(DuplicateEmailException ex, HttpServletRequest req) {
        return build(HttpStatus.CONFLICT, "Conflict", ex.getMessage(), req.getRequestURI(), null);
    }

    @ExceptionHandler({ MethodArgumentNotValidException.class, BindException.class })
    public ResponseEntity<UVOceanWavesApiError> handleValidation(Exception ex, HttpServletRequest req) {
        List<String> errors = null;
        if (ex instanceof MethodArgumentNotValidException manv) {
            errors = manv.getBindingResult().getFieldErrors()
                    .stream().map(fe -> fe.getField() + ": " + fe.getDefaultMessage()).toList();
        } else if (ex instanceof BindException be) {
            errors = be.getBindingResult().getFieldErrors()
                    .stream().map(fe -> fe.getField() + ": " + fe.getDefaultMessage()).toList();
        }
        return build(HttpStatus.BAD_REQUEST, "Validation Failed", "Request has validation errors",
                req.getRequestURI(), errors);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<UVOceanWavesApiError> handleConstraintViolation(ConstraintViolationException ex, HttpServletRequest req) {
        List<String> errors = ex.getConstraintViolations().stream()
                .map(v -> v.getPropertyPath() + ": " + v.getMessage()).toList();
        return build(HttpStatus.BAD_REQUEST, "Validation Failed", "Constraint violations in request",
                req.getRequestURI(), errors);
    }

    @ExceptionHandler(ErrorResponseException.class)
    public ResponseEntity<UVOceanWavesApiError> handleErrorResponse(ErrorResponseException ex, HttpServletRequest req) {
        HttpStatus status = (HttpStatus) ex.getStatusCode();
        return build(status, status.getReasonPhrase(), ex.getMessage(), req.getRequestURI(), null);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<UVOceanWavesApiError> handleGeneric(Exception ex, HttpServletRequest req) {
        return build(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error", ex.getMessage(),
                req.getRequestURI(), null);
    }

    private ResponseEntity<UVOceanWavesApiError> build(HttpStatus status, String error, String message, String path,
                                           List<String> validationErrors) {
        UVOceanWavesApiError body = new UVOceanWavesApiError(Instant.now(), status.value(), error, message, path, validationErrors);
        return ResponseEntity.status(status).body(body);
    }
}
