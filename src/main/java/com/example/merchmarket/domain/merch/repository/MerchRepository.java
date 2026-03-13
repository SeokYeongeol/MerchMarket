package com.example.merchmarket.domain.merch.repository;

import com.example.merchmarket.domain.merch.entity.Merch;
import com.example.merchmarket.domain.merch.enums.MerchState;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MerchRepository extends JpaRepository<Merch, Long> {

    @Query("select m from Merch m where m.merchState = :merchState")
    Page<Merch> findAllByMerchState(MerchState merchState, Pageable pageable);
}
