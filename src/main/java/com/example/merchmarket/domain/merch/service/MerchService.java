package com.example.merchmarket.domain.merch.service;

import com.example.merchmarket.domain.merch.dto.response.MerchResponse;
import com.example.merchmarket.domain.merch.entity.Merch;
import com.example.merchmarket.domain.merch.repository.MerchRepository;
import com.example.merchmarket.global.exception.ServerException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PagedModel;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.example.merchmarket.domain.merch.enums.MerchState.IN_STOCK;
import static com.example.merchmarket.global.exception.ErrorCode.MERCH_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class MerchService {

    private final MerchRepository merchRepository;

    @Transactional(readOnly = true)
    public MerchResponse findMerch(Long merchId) {
        Merch findMerch = merchRepository.findById(merchId)
                .orElseThrow(() -> new ServerException(MERCH_NOT_FOUND));
        return MerchResponse.of(findMerch);
    }

    @Transactional(readOnly = true)
    public PagedModel<MerchResponse> findAllMerches(int page) {
        Pageable pageable = PageRequest.of(page - 1, 10,
                Sort.by(Sort.Direction.DESC, "createdAt"));

        Page<Merch> findMerches = merchRepository.findAllByMerchState(IN_STOCK, pageable);
        return new PagedModel<>(findMerches.map(MerchResponse::of));
    }
}
