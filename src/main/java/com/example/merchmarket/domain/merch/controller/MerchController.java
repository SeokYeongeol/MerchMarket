package com.example.merchmarket.domain.merch.controller;

import com.example.merchmarket.domain.merch.dto.response.MerchResponse;
import com.example.merchmarket.domain.merch.service.MerchService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.web.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MerchController {

    private final MerchService merchService;

    @GetMapping("/v1/merches/{merchId}")
    public ResponseEntity<MerchResponse> findMerch(@PathVariable Long merchId) {
        return ResponseEntity.ok(merchService.findMerch(merchId));
    }

    @GetMapping("/v1/merches")
    public ResponseEntity<PagedModel<MerchResponse>> findAllMerches(@RequestParam int page) {
        return ResponseEntity.ok(merchService.findAllMerches(page));
    }
}
