package com.example.merchmarket.domain.merch.dto.response;

import com.example.merchmarket.domain.merch.entity.Merch;
import com.example.merchmarket.domain.merch.enums.MerchState;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class MerchResponse {

    private final Long merchId;
    private final String merchName;
    private final Integer merchPrice;
    private final Integer merchQuantity;
    private final MerchState merchState;

    public static MerchResponse of(Merch merch) {
        return new MerchResponse(
                merch.getId(),
                merch.getMerchName(),
                merch.getMerchPrice(),
                merch.getMerchQuantity(),
                merch.getMerchState()
        );
    }
}
