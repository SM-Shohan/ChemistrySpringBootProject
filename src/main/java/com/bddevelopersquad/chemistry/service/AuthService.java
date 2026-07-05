package com.bddevelopersquad.chemistry.service;

import com.bddevelopersquad.chemistry.dto.ApiResponse;
import com.bddevelopersquad.chemistry.dto.SecretLoginRequest;
import com.bddevelopersquad.chemistry.dto.SecretLoginResponse;
import com.bddevelopersquad.chemistry.security.JwtUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthService {
    private final JwtUtil jwtUtil;
    public SecretLoginResponse secretLogin(SecretLoginRequest request) {
        String token = jwtUtil.generateAccessToken(request.getUserId());
        return new SecretLoginResponse(token);
    }
}
