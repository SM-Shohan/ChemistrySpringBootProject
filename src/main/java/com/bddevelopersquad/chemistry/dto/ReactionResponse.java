package com.bddevelopersquad.chemistry.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ReactionResponse {
    private Long id;
    private String searchKeys;
    private String equation;
    private String details;
    private int keywordCount;
}
