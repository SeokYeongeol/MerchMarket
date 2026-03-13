package com.example.merchmarket.domain.merch.entity;

import com.example.merchmarket.domain.admin.entity.Admin;
import com.example.merchmarket.domain.merch.enums.MerchState;
import com.example.merchmarket.global.entity.TimeStamped;
import com.example.merchmarket.global.exception.ServerException;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static com.example.merchmarket.global.exception.ErrorCode.CANNOT_BUY_MORE_QUANTITY;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Merch extends TimeStamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String merchName;
    private Integer merchPrice;
    private Integer merchQuantity;

    @Enumerated(EnumType.STRING)
    private MerchState merchState;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "admin_id", nullable = false)
    private Admin admin;

    @Builder
    public Merch(String merchName, Integer merchPrice, Integer merchQuantity, MerchState merchState, Admin admin) {
        this.merchName = merchName;
        this.merchPrice = merchPrice;
        this.merchQuantity = merchQuantity;
        this.merchState = merchState;
        this.admin = admin;
    }

    public void buyMerch(Integer quantity) {
        if (quantity > merchQuantity) throw new ServerException(CANNOT_BUY_MORE_QUANTITY);
        this.merchQuantity -= quantity;
    }
}
