package com.example.Product.Manager.Exception;

import com.example.Product.Manager.Response.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Map<String, String>>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        log.error("Validation failed: {}", ex.getMessage());
        Map<String, String> errors = new HashMap<>();

        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.put(error.getField(), error.getDefaultMessage());
        }

        ApiResponse<Map<String, String>> response = new ApiResponse<>(
                "fail",
                HttpStatus.BAD_REQUEST.value(),
                "Validation failed. Please check your input.",
                true,
                errors
        );

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ApiResponse<Void>> handleProductNotFound(ProductNotFoundException ex) {
        log.warn("Product not found: {}", ex.getMessage());

        ApiResponse<Void> response = new ApiResponse<>(
                "fail",
                HttpStatus.NOT_FOUND.value(),
                ex.getMessage(),
                true,
                null
        );

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidSkuFormatException.class)
    public ResponseEntity<ApiResponse<Void>> handleInvalidSkuFormat(InvalidSkuFormatException ex) {
        log.error("Invalid SKU format: {}", ex.getMessage());

        ApiResponse<Void> response = new ApiResponse<>(
                "fail",
                HttpStatus.BAD_REQUEST.value(),
                ex.getMessage(),
                true,
                null
        );

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(SkuAlreadyExistsException.class)
    public ResponseEntity<ApiResponse<Void>> handleSkuAlreadyExists(SkuAlreadyExistsException ex) {
        log.warn("Duplicate SKU: {}", ex.getMessage());

        ApiResponse<Void> response = new ApiResponse<>(
                "fail",
                HttpStatus.CONFLICT.value(),
                ex.getMessage(),
                true,
                null
        );

        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Void>> handleGenericException(Exception ex) {
        log.error("Unexpected error occurred: {}", ex.getMessage(), ex);

        ApiResponse<Void> response = new ApiResponse<>(
                "error",
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "An unexpected error occurred: " + ex.getMessage(),
                true,
                null
        );

        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
