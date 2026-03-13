package com.example.merchmarket.domain.merch.service;

import com.example.merchmarket.domain.admin.entity.Admin;
import com.example.merchmarket.domain.merch.dto.request.CreateMerchRequest;
import com.example.merchmarket.domain.merch.dto.response.MerchResponse;
import com.example.merchmarket.domain.merch.entity.Merch;
import com.example.merchmarket.domain.merch.repository.MerchRepository;
import com.example.merchmarket.global.entity.Auth;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.example.merchmarket.domain.merch.enums.MerchState.IN_STOCK;

@Service
@RequiredArgsConstructor
public class AdminMerchService {

    private final MerchRepository merchRepository;

    @Transactional
    public MerchResponse createMerch(Auth auth, CreateMerchRequest request) {
        Admin findAdmin = Admin.fromAuth(auth.getId());

        Merch savedMerch = Merch.builder()
                .merchName(request.getMerchName())
                .merchPrice(request.getMerchPrice())
                .merchQuantity(request.getMerchQuantity())
                .merchState(IN_STOCK)
                .admin(findAdmin)
                .build();
        merchRepository.save(savedMerch);
        return MerchResponse.of(savedMerch);
    }
}
