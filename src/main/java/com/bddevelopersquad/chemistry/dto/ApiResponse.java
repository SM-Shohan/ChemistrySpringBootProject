package com.bddevelopersquad.chemistry.dto;

import com.bddevelopersquad.chemistry.utils.ApiStatus;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ApiResponse<T> {
    private ApiStatus status;
    private int code;
    private String message;
    private T data;
    private ErrorResponse error;
    private MetaData meta;

    public static <T> ApiResponse<T> success(String message, T data) {
        return new ApiResponse<T>(ApiStatus.SUCCESS, 200, message, data, null, new MetaData(LocalDateTime.now()));
    }

    public static <T> ApiResponse<T> success(String message) {
        return new ApiResponse<T>(ApiStatus.SUCCESS, 200, message, null, null, new MetaData(LocalDateTime.now()));
    }

    public static <T> ApiResponse<T> error(int code, String message, ErrorResponse error) {
        return new ApiResponse<T>(ApiStatus.ERROR, code, message, null, error, new MetaData(LocalDateTime.now()));
    }
}
