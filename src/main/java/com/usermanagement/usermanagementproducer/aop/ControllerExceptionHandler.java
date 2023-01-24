package com.usermanagement.usermanagementproducer.aop;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler({MethodArgumentNotValidException.class})
    protected ResponseEntity<Object> handleConflict(
            MethodArgumentNotValidException ex, WebRequest request) {
        List<FieldError> fieldErrors = ex.getFieldErrors();
        String errorMessages = fieldErrors.stream().map(fieldError -> fieldError.getField()
                                                                        + " " + fieldError.getDefaultMessage())
                .collect(Collectors.joining("/"));
        return ResponseEntity.badRequest().body(errorMessages);

    }
}
