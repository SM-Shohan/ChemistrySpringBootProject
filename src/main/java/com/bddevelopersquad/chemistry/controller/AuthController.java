package com.bddevelopersquad.chemistry.controller;

import com.bddevelopersquad.chemistry.dto.ApiResponse;
import com.bddevelopersquad.chemistry.dto.SecretLoginRequest;
import com.bddevelopersquad.chemistry.dto.SecretLoginResponse;
import com.bddevelopersquad.chemistry.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;
    @PostMapping("/secret_login")
    public ResponseEntity<ApiResponse<SecretLoginResponse>> secretLogin(@Valid @RequestBody SecretLoginRequest request) {
        return ResponseEntity.ok(ApiResponse.success("Login Successful", authService.secretLogin(request)));
    }
}
