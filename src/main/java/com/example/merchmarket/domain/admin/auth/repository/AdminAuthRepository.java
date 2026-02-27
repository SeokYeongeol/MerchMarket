package com.example.merchmarket.domain.admin.auth.repository;

import com.example.merchmarket.domain.admin.auth.entity.Admin;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface AdminAuthRepository extends JpaRepository<Admin, Long> {
    boolean existsByEmail(@Param("email") String email);

    boolean existsByName(@Param("name")  String name);

    @Query("select a from Admin a where a.email = :email")
    Optional<Admin> findByEmail(@Param("email") String email);
}
