package com.example.tikicloned.exception;

import com.example.tikicloned.common.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Optional.ofNullable;


@ControllerAdvice
@Slf4j
@RequiredArgsConstructor
public class GlobalExceptionHandler {
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<ApiResponse<String>> handleMissingServletRequestParameterException(MissingServletRequestParameterException ex) {
        return ResponseEntity
                .badRequest()
                .body(ApiResponse.<String>builder()
                        .errorCode(String.valueOf(HttpStatus.BAD_REQUEST.value()))
                        .messages(List.of(ex.getMessage()))
                        .data(null)
                        .timestamp(LocalDateTime.now())
                        .build());
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ApiResponse<Map<String, String>>> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex) {
        Map<String, String> details = new HashMap<>();
        details.put("paramName", ex.getName());
        details.put("paramValue", ofNullable(ex.getValue()).map(Object::toString).orElse(""));
        details.put("errorMessage", ex.getMessage());
        return ResponseEntity
                .badRequest()
                .body(ApiResponse.<Map<String, String>>builder()
                        .errorCode(String.valueOf(HttpStatus.BAD_REQUEST.value()))
                        .messages(List.of(ex.getMessage()))
                        .data(details)
                        .timestamp(LocalDateTime.now())
                        .build());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Map<String, String>>> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        BindingResult bindingResult = ex.getBindingResult();
        HashMap<String, String> fieldErrors = new HashMap<>();
        bindingResult.getFieldErrors().forEach(fieldError -> {
            fieldErrors.put(fieldError.getField(), fieldError.getDefaultMessage());
        });
        return ResponseEntity
                .badRequest()
                .body(ApiResponse.<Map<String, String>>builder()
                        .errorCode(String.valueOf(HttpStatus.BAD_REQUEST.value()))
                        .messages(List.of(ex.getMessage()))
                        .data(fieldErrors)
                        .timestamp(LocalDateTime.now())
                        .build());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<String>> handleInternalServerError(Exception ex) {
        return ResponseEntity
                .internalServerError()
                .body(ApiResponse.<String>builder()
                        .errorCode(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()))
                        .messages(List.of(ex.getMessage()))
                        .data(null)
                        .timestamp(LocalDateTime.now())
                        .build());
    }
}
