package com.bddevelopersquad.chemistry.controller;

import com.bddevelopersquad.chemistry.dto.ApiResponse;
import com.bddevelopersquad.chemistry.dto.ReactionResponse;
import com.bddevelopersquad.chemistry.dto.SaveReactionRequest;
import com.bddevelopersquad.chemistry.service.ReactionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reactions")
@RequiredArgsConstructor
public class ReactionController {
    private final ReactionService reactionService;

    @PostMapping
    public ResponseEntity<ApiResponse<ReactionResponse>> create(@Valid @RequestBody SaveReactionRequest request) {
        return ResponseEntity.ok(ApiResponse.success("Reaction added database",reactionService.save(request)));
    }

    @GetMapping("/search")
    public ResponseEntity<ApiResponse<List<ReactionResponse>>> search(
            @RequestParam List<String> keywords,
            @RequestParam(defaultValue = "15") int limit
    ) {
        return ResponseEntity.ok(ApiResponse.success("Reaction searched",reactionService.search(keywords, limit)));
    }

}
