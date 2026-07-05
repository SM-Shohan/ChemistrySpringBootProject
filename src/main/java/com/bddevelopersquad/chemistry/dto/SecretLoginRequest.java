package com.bddevelopersquad.chemistry.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class SecretLoginRequest {
    @NotBlank
    private String userId;
}
