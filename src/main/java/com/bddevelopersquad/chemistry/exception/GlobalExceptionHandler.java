package com.bddevelopersquad.chemistry.exception;

import com.bddevelopersquad.chemistry.dto.ApiResponse;
import com.bddevelopersquad.chemistry.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<?>> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        List<String> details = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> error.getField() + ":" + error.getDefaultMessage())
                .toList();
        return build(HttpStatus.BAD_REQUEST, "Validation failed", new ErrorResponse(400, "BAD_REQUEST", details));
    }




    private ResponseEntity<ApiResponse<?>> build(HttpStatus status, String message, ErrorResponse error){
        return ResponseEntity.status(status).body( ApiResponse.error(status.value(),message,error));
    }
}
;