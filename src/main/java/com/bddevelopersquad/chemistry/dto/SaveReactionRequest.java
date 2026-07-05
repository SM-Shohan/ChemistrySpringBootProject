package com.bddevelopersquad.chemistry.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class SaveReactionRequest {
    @NotBlank
    private String searchKeys;
    @NotBlank
    private String equation;
    private String details;
}
