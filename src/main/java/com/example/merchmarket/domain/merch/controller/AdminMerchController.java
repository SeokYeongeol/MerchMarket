package com.example.merchmarket.domain.merch.controller;

import com.example.merchmarket.domain.merch.dto.request.CreateMerchRequest;
import com.example.merchmarket.domain.merch.dto.response.MerchResponse;
import com.example.merchmarket.domain.merch.service.AdminMerchService;
import com.example.merchmarket.global.entity.Auth;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class AdminMerchController {

    private final AdminMerchService adminMerchService;

    @PostMapping("/v1/admin/merches")
    public ResponseEntity<MerchResponse> createMerch(
        @AuthenticationPrincipal Auth auth,
        @Valid @RequestBody CreateMerchRequest request
    ) {
        return ResponseEntity.ok(adminMerchService.createMerch(auth, request));
    }
}
