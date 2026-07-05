package com.bddevelopersquad.chemistry.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class MetaData {
    private LocalDateTime timestamp;
}
