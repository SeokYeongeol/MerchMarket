package com.example.merchmarket.domain.admin.auth.controller;

import com.example.merchmarket.domain.admin.auth.dto.request.AdminSignUpRequest;
import com.example.merchmarket.domain.admin.auth.service.AdminAuthService;
import com.example.merchmarket.domain.auth.dto.request.LoginRequest;
import com.example.merchmarket.domain.auth.dto.response.AuthResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class AdminAuthController {

    private final AdminAuthService adminAuthService;

    @PostMapping("/v1/admin/auth/signup")
    public ResponseEntity<AuthResponse> adminSignUp(@Valid @RequestBody AdminSignUpRequest request) {
        return ResponseEntity.ok(adminAuthService.adminSignUp(request));
    }

    @PostMapping("/v1/admin/auth/login")
    public ResponseEntity<AuthResponse> adminLogin(@Valid @RequestBody LoginRequest request) {
        return ResponseEntity.ok(adminAuthService.adminLogin(request));
    }

}
