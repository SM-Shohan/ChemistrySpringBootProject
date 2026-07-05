package com.bddevelopersquad.chemistry.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ErrorResponse {
    private int errorCode;
    private String errorMessage;
    private List<String> details;
}
