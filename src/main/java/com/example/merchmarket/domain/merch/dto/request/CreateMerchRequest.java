package com.example.merchmarket.domain.merch.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class CreateMerchRequest {

    @NotNull
    private String merchName;

    @NotNull
    private Integer merchPrice;

    @NotNull
    private Integer merchQuantity;
}
